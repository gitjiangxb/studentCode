package com.nxtele.did.dao;
import com.nxtele.did.entity.PartitionInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartitionOperationDao {

    /**
     * 得到分区表信息
     * @param tableSchema   库名
     * @param tableName     表名
     * @return
     */
    List<PartitionInfo> getPartitionInfo(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

    /**
     * 检查表字段是否是主键
     * @param tableSchema   库名
     * @param tableName     表名
     * @param fieldName     字段
     * @return
     */
    String checkFieldIsAPrimaryKey(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName, @Param("fieldName") String fieldName);

    /**
     * 创建分区
     * @param tableName         表名
     * @param partitionField    分区字段
     * @param partitionName     分区名
     * @param partitionInterval 分区区间段
     * @return
     */
    int createPartition(@Param("tableName") String tableName, @Param("partitionField") String partitionField, @Param("partitionName") String partitionName, @Param("partitionInterval") String partitionInterval);

    /**
     * 添加分区
     * @param tableName         表名
     * @param partitionName     分区名
     * @param partitionInterval 分区区间段
     * @return
     */
    int addPartition(@Param("tableName") String tableName, @Param("partitionName") String partitionName, @Param("partitionInterval") String partitionInterval);

    /**
     * 删除分区
     * @param tableName         表名
     * @param partitionName     分区名
     * @return
     */
    int deletePartition(@Param("tableName") String tableName, @Param("partitionName") String partitionName);

    /**
     * 创建备份表
     * @param tableName         源表
     * @param backupTableName   目标表
     * @return
     */
    int createBackupTable(@Param("tableName") String tableName, @Param("backupTableName") String backupTableName);

    /**
     * 切出分区前必须调用这个
     * @param backupTableName   目标表
     * @return
     */
    int removePartitioning(@Param("backupTableName") String backupTableName);

    /**
     * 切出分区，将源表中的分区数据拷贝到目标表中，注：源表的分区还存在，只是没有数据
     * @param tableName         源表
     * @param partitionName     分区名
     * @param backupTableName   目标表
     * @return
     */
    int cuttingOutPartitioning(@Param("tableName") String tableName, @Param("partitionName") String partitionName, @Param("backupTableName") String backupTableName);
}
