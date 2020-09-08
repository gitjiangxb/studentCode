package com.nxtele.did.dto;

/**
 * DID号码配置接入方式为——呼叫转移
 * @Author: Jiangxb
 * @Date: 2020/08/17 13:41
 */
public class TransferNumberDto {
    private long countryId;
    private String number;

    public TransferNumberDto(long countryId, String number) {
        this.countryId = countryId;
        this.number = number;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
