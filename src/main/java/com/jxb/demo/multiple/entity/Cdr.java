package com.jxb.demo.multiple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "did_cdr")
public class Cdr implements Serializable {
    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
    private Long voscdrId;
    private Long customerId;
    private Long numberId;
    private String number;
    private String callNumber;
    private Integer callType;
    private Long countryId;
    private Integer callDuration;
    private Integer feeDuration;
    private String transferNumber;
    private BigDecimal callPrice;
    private BigDecimal transferPrice;
    private BigDecimal callCost;
    private BigDecimal transferCost;
    private BigDecimal profit;
    private Integer endDirection;
    private Integer endReason;
    private String callerIp;
    private String calleeIp;
    private Date startTime;
    private Date endTime;
    private Date gmtCreate;
    private Date gmtMobified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoscdrId() {
        return voscdrId;
    }

    public void setVoscdrId(Long voscdrId) {
        this.voscdrId = voscdrId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public Integer getCallType() {
        return callType;
    }

    public void setCallType(Integer callType) {
        this.callType = callType;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Integer getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(Integer callDuration) {
        this.callDuration = callDuration;
    }

    public Integer getFeeDuration() {
        return feeDuration;
    }

    public void setFeeDuration(Integer feeDuration) {
        this.feeDuration = feeDuration;
    }

    public String getTransferNumber() {
        return transferNumber;
    }

    public void setTransferNumber(String transferNumber) {
        this.transferNumber = transferNumber;
    }

    public BigDecimal getCallCost() {
        return callCost;
    }

    public void setCallCost(BigDecimal callCost) {
        this.callCost = callCost;
    }

    public BigDecimal getTransferCost() {
        return transferCost;
    }

    public void setTransferCost(BigDecimal transferCost) {
        this.transferCost = transferCost;
    }

    public BigDecimal getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(BigDecimal callPrice) {
        this.callPrice = callPrice;
    }

    public BigDecimal getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(BigDecimal transferPrice) {
        this.transferPrice = transferPrice;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Integer getEndDirection() {
        return endDirection;
    }

    public void setEndDirection(Integer endDirection) {
        this.endDirection = endDirection;
    }

    public Integer getEndReason() {
        return endReason;
    }

    public void setEndReason(Integer endReason) {
        this.endReason = endReason;
    }

    public String getCallerIp() {
        return callerIp;
    }

    public void setCallerIp(String callerIp) {
        this.callerIp = callerIp;
    }

    public String getCalleeIp() {
        return calleeIp;
    }

    public void setCalleeIp(String calleeIp) {
        this.calleeIp = calleeIp;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtMobified() {
        return gmtMobified;
    }

    public void setGmtMobified(Date gmtMobified) {
        this.gmtMobified = gmtMobified;
    }
}
