package com.nxtele.did.entity;

import java.io.Serializable;

public class PartitionInfo implements Serializable {
    private String tableSchema;
    private String tableName;
    private String tableRows;
    private String partitionName;
    private String partitionOrdinalPosition;
    // 分区模式：RANGE
    private String partitionMethod;
    // 分区表达式：TO_DAYS(gmt_create)
    private String partitionExpression;

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

    public String getTableRows() {
        return tableRows;
    }

    public void setTableRows(String tableRows) {
        this.tableRows = tableRows;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    public String getPartitionOrdinalPosition() {
        return partitionOrdinalPosition;
    }

    public void setPartitionOrdinalPosition(String partitionOrdinalPosition) {
        this.partitionOrdinalPosition = partitionOrdinalPosition;
    }

    public String getPartitionMethod() {
        return partitionMethod;
    }

    public void setPartitionMethod(String partitionMethod) {
        this.partitionMethod = partitionMethod;
    }

    public String getPartitionExpression() {
        return partitionExpression;
    }

    public void setPartitionExpression(String partitionExpression) {
        this.partitionExpression = partitionExpression;
    }
}
