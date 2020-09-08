package com.nxtele.did.vos69Dao;

import com.nxtele.did.entity.VosECdr;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Vos69ECdrDao {
    List<VosECdr> getAllCdr();

    /**
     * 根据 DID VOS的被叫id  去 69 VOS的主叫id模糊查询
     * @param calleecallid      被叫id
     * @param tableName         表名
     * @return
     */
    Double getTransferCost(@Param("calleecallid") String calleecallid, @Param("tableName")  String tableName);
}
