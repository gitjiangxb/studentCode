package com.nxtele.did.service;


import com.nxtele.did.dto.PartitionDto;
import com.nxtele.did.entity.PartitionInfo;

import java.util.List;

/**
 * 分区操作
 */
public interface PartitionOperationService {

    /**
     * 得到分区表信息
     * @param tableSchema   库名
     * @param tableName     表名
     * @return
     */
    List<PartitionInfo> getPartitionInfo(String tableSchema, String tableName);

    /**
     * 创建分区
     * @param partitionDto
     * @return
     */
    boolean createPartition(PartitionDto partitionDto);

    /**
     * 删除分区
     * @param tableName     表名
     * @param partitionName 分区名
     * @return
     */
    boolean deletePartition(String tableName, String partitionName);

    /**
     *  备份分区数据
     * @param tableName         需要备份的源表
     * @param backupTableName   需要备份到的目标表
     * @param partitionName     分区名
     * @return
     */
    boolean backupPartitionData(String tableName, String backupTableName, String partitionName);

}
