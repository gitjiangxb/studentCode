package com.nxtele.did.dto;

import com.nxtele.did.enums.PartitionDateType;

import java.io.Serializable;

public class PartitionDto implements Serializable {
    private String tableSchema;
    private String tableName;
    /**
     *是否需要备份旧数据(备份周期从配置文件中backup_cycle获取)
     */
    private boolean isBackup;
    /**
     *是否需要删除旧数据和旧分区
     */
    private boolean isDelete;
    /**
     * 分区字段(创建表时必须为主键)
     */
    private String partitionField;
    /**
     * 分区字段的日期类型
     */
    private PartitionDateType partitionDateType;
    /**
     * 不是分区表时，是否修改其为分区表
     *  true：partitionField不能为空
     */
    private boolean doesNotExistCreate;

    /**
     * 创建分区的个数
     */
    private int createPartitionNum;

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean getIsBackup() {
        return isBackup;
    }

    public void setIsBackup(boolean backup) {
        isBackup = backup;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean delete) {
        isDelete = delete;
    }

    public String getPartitionField() {
        return partitionField;
    }

    public void setPartitionField(String partitionField) {
        this.partitionField = partitionField;
    }

    public boolean getDoesNotExistCreate() {
        return doesNotExistCreate;
    }

    public void setDoesNotExistCreate(boolean doesNotExistCreate) {
        this.doesNotExistCreate = doesNotExistCreate;
    }

    public int getCreatePartitionNum() {
        return createPartitionNum;
    }

    public void setCreatePartitionNum(int createPartitionNum) {
        this.createPartitionNum = createPartitionNum;
    }

    public PartitionDateType getPartitionDateType() {
        return partitionDateType;
    }

    public void setPartitionDateType(PartitionDateType partitionDateType) {
        this.partitionDateType = partitionDateType;
    }
}
