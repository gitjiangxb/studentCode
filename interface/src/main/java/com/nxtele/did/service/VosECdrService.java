package com.nxtele.did.service;

import com.nxtele.did.entity.VosECdr;

import java.util.List;

public interface VosECdrService {

    /**
     * 根据时间得到VOS中的CDR信息
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param tableName     分区表名
     * @return
     */
    List<VosECdr> getVosCDRByTimeAndTableName(String startTime, String endTime, String tableName);

    /**
     * 测试69vos的CDR
     * @return
     */
    List<VosECdr> getAllCdr();

    /**
     * 根据 DID VOS的被叫id  去 69 VOS的主叫id模糊查询
     * @param calleecallid      被叫id
     * @param tableName         表名
     * @return
     */
    Double getTransferCost(String calleecallid, String tableName);
}
