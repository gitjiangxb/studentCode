package com.jxb.demo.multiple.vosDao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxb.demo.multiple.entity.VosECdr;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VosECdrDao extends BaseMapper<VosECdr> {
    List<VosECdr> getAllCdr();

    /**
     * 根据时间得到VOS中的CDR信息
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param tableName     分区表名
     * @return
     */
    List<VosECdr> getVosCDRByTimeAndTableName(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("tableName") String tableName);
}
