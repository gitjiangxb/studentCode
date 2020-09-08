package com.nxtele.did.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.nxtele.did.dao.PartitionOperationDao;
import com.nxtele.did.dto.PartitionDto;
import com.nxtele.did.entity.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartitionOperationServiceImpl implements PartitionOperationService {

    private final Logger logger = LoggerFactory.getLogger(PartitionOperationServiceImpl.class);

    /* 表分区名前缀 */
    private final static String PARTITION_PREFIX = "p";
    /**
     * 分区数据过期天数
     */
    @Value("${backup_cycle}")
    private Integer backup_cycle;

    @Autowired
    private PartitionOperationDao partitionOperationDao;

    @Override
    public List<PartitionInfo> getPartitionInfo(String tableSchema, String tableName) {
        return partitionOperationDao.getPartitionInfo(tableSchema,tableName);
    }

    @Override
    public boolean createPartition(PartitionDto partitionDto) {
        if (!checkParam(partitionDto)){
            return false;
        }

        Map<String,String> partitionNameMap = new HashMap<>();

        // 获取表的分区信息
        List<PartitionInfo> partitionInfoList = partitionOperationDao.getPartitionInfo(partitionDto.getTableSchema(), partitionDto.getTableName());
        if (partitionInfoList == null || partitionInfoList.size() == 0){
            logger.error("{}库中不存在{}表！",partitionDto.getTableSchema(),partitionDto.getTableName());
            return false;
        }

        // 不是分区表时，是否修改为分区表(参数的校验)
        if (partitionDto.getDoesNotExistCreate()){
            if (StringUtils.isEmpty(partitionDto.getPartitionField())){
                logger.error("修改{}库的{}表为分区表时,分区字段{}不能为空！",partitionDto.getTableSchema(),partitionDto.getTableName(),partitionDto.getPartitionField());
                return false;
            }
            String column_key = partitionOperationDao.checkFieldIsAPrimaryKey(partitionDto.getTableSchema(), partitionDto.getTableName(), partitionDto.getPartitionField());
            if (StringUtils.isEmpty(column_key) || !"PRI".equals(column_key)){
                logger.error("{}字段在{}库的{}表中不是主键,因此不能将其修改为分区表！",partitionDto.getPartitionField(),partitionDto.getTableSchema(),partitionDto.getTableName());
                return false;
            }
        }

        // 当前天
        String nowadays = DateUtil.format(new Date(), "yyyyMMdd");
        // 分区名称
        String newPartitionName = PARTITION_PREFIX + nowadays;
        // 分区区间(当前天+1)
        String partitionInterval = DateUtil.format(DateUtil.offsetDay(new Date(), 1), "yyyyMMdd");

        // 存在表，但还不是分区表
        if (partitionInfoList.size() == 1 && StringUtils.isEmpty(partitionInfoList.get(0).getPartitionName())){
            if (partitionDto.getDoesNotExistCreate()){
                try {
                    // 表中不存在分区时，创建分区
                    partitionOperationDao.createPartition(
                            partitionDto.getTableName(),
                            partitionDto.getPartitionField(),
                            newPartitionName,
                            partitionInterval
                    );

                    // 创建完分区后,再根据指定添加分区数去添加分区
                    this.addPartition(partitionNameMap,partitionDto.getTableName(),partitionDto.getCreatePartitionNum(),true);

                    logger.info("为{}库中{}表创建{}分区成功",partitionDto.getTableSchema(),partitionDto.getTableName(),newPartitionName);
                    return true;
                } catch (Exception e){
                    e.printStackTrace();
                    logger.error("为{}库中{}表创建{}分区时，发生错误",partitionDto.getTableSchema(),partitionDto.getTableName(),newPartitionName);
                    return false;
                }
            } else {
                logger.error("{}库中{}表还未进行分区,无法对其管理！",partitionDto.getTableSchema(),partitionDto.getTableName());
                return false;
            }
        }

        /**
         * 遍历分区信息主要完成两步：
         *  1、是否需要备份旧数据
         *  2、是否需要删除旧数据和旧分区
         */
        for (PartitionInfo partitionInfo : partitionInfoList){
            String partitionName = partitionInfo.getPartitionName();
            partitionNameMap.put(partitionName,partitionName);
            // 分区天
            String dateTime = partitionName.replace(PARTITION_PREFIX, "");
            Date partitionDay = DateUtil.parse(dateTime);   // 分区时间
            Date dateNow = DateUtil.parse(nowadays);        // 当前时间
            // 示例： ("20200509", "20200507", DateUnit.DAY, false) = -2
            long days = DateUtil.between(dateNow, partitionDay, DateUnit.DAY, false);
            // 判断当前分区数据是否过期
            if (backup_cycle < -days){
                // 备份旧分区数据
                if (partitionDto.getIsBackup()){
                    String backupTableName = partitionDto.getTableName() + "_" + dateTime;
                    boolean result = this.backupPartitionData(
                            partitionDto.getTableName(),
                            backupTableName,
                            partitionName
                    );
                    if (!result){
                        logger.error("备份{}表{}分区数据到{}表时,发生错误！",partitionDto.getTableName(),partitionName,backupTableName);
                        return false;
                    }
                    logger.info("备份{}表{}分区数据到{}表,操作成功",partitionDto.getTableName(),partitionName,backupTableName);
                }

                // 删除旧数据和旧分区
                if (partitionDto.getIsDelete()){
                    boolean result = this.deletePartition(partitionDto.getTableName(), partitionName);
                    if (!result){
                        logger.error("删除{}表的{}分区数据时，发生错误！",partitionDto.getTableName(),partitionName);
                        return false;
                    }
                    logger.error("删除{}表的{}分区数据.操作成功",partitionDto.getTableName(),partitionName);
                }
            }
        }

        return this.addPartition(partitionNameMap,partitionDto.getTableName(),partitionDto.getCreatePartitionNum(),false);
    }

    /**
     * 根据指定数量创建多少个分区
     * @param partitionNameMap      已经存在的分区名
     * @param tableName             需要创建分区的表
     * @param createPartitionNum    创建分区的数量
     * @param isFirst               是否是第一次创建分区{若是第一次创建,添加分区方法就需要少调用一次}
     * @return
     */
    private boolean addPartition(Map<String,String> partitionNameMap, String tableName, int createPartitionNum, boolean isFirst){
        int startNum = 0;
        if (isFirst){
            // 若是第一次创建分区,添加分区的时间应该是第二天
            startNum = 1;
        }
        for (int i = startNum ; i < createPartitionNum ; i++){
            // 当前天
            String nowadays = DateUtil.format(DateUtil.offsetDay(new Date(), i), "yyyyMMdd");
            // 分区名称
            String newPartitionName = PARTITION_PREFIX + nowadays;
            // 分区区间(当前天+1)
            String partitionInterval = DateUtil.format(DateUtil.offsetDay(new Date(), i + 1), "yyyyMMdd");

            System.out.println("分区名称:" + newPartitionName + "    分区区间:" + partitionInterval);
            if (!partitionNameMap.containsKey(newPartitionName)){
                // 当前表的分区中不包含当天的分区时，添加进去
                try {
                    partitionOperationDao.addPartition(
                            tableName,
                            newPartitionName,
                            partitionInterval
                    );
                    logger.info("{}表,添加当天的分区{},操作成功",tableName,newPartitionName);
                } catch (Exception e){
                    e.printStackTrace();
                    logger.error("{}表,添加当天的分区{}时，发生错误",tableName,newPartitionName);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkParam(PartitionDto partitionDto) {
        if (StringUtils.isEmpty(partitionDto.getTableSchema())){
            logger.error("创建分区时,库名不能为空");
            return false;
        }

        if (StringUtils.isEmpty(partitionDto.getTableName())){
            logger.error("创建分区时,表名不能为空");
            return false;
        }

        return true;
    }


    @Override
    public boolean deletePartition(String tableName, String partitionName) {
        try {
            partitionOperationDao.deletePartition(tableName, partitionName);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("删除{}表的{}分区时，发生错误！",tableName,partitionName);
            return false;
        }
    }

    @Override
    public boolean backupPartitionData(String tableName, String backupTableName, String partitionName) {
        boolean createBackupTable = this.createBackupTable(tableName, backupTableName);
        if (createBackupTable){
            boolean removePartitioning = this.removePartitioning(backupTableName);
            if (removePartitioning){
                boolean cuttingOutPartitioning = this.cuttingOutPartitioning(tableName, partitionName, backupTableName);
                if (cuttingOutPartitioning){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean createBackupTable(String tableName, String backupTableName){
        try {
            partitionOperationDao.createBackupTable(tableName,backupTableName);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("创建分区数据备份表{}时，发生错误！",tableName);
            return false;
        }
    }

    private boolean removePartitioning(String backupTableName){
        try {
            partitionOperationDao.removePartitioning(backupTableName);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("REMOVE PARTITIONING分区备份表{}时，发生错误！",backupTableName);
            return false;
        }
    }

    private boolean cuttingOutPartitioning(String tableName, String backupTableName, String partitionName){
        try {
            partitionOperationDao.cuttingOutPartitioning(tableName,backupTableName,partitionName);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("切出分区,将源表{}中的{}分区数据拷贝到目标表{}时，发生错误！",tableName,partitionName,backupTableName);
            return false;
        }
    }
}
