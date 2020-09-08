package com.nxtele.did.enums;

/**
 *
 */
public enum PartitionDateType {
    TO_DAYS("TO_DAYS","yyyyMMdd"),
    UNIX_TIMESTAMP("UNIX_TIMESTAMP","yyyy-MM-dd") //自动补00:00:00
    ;
    private String name;
    private String format;

    PartitionDateType(String name, String format) {
        this.name = name;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
