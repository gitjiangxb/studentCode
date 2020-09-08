package com.nxtele.did.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nxtele.did.entity.VosECdr;
import com.nxtele.did.vos69Dao.Vos69ECdrDao;
import com.nxtele.did.vosDao.VosECdrDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class VosECdrServiceImpl extends ServiceImpl<VosECdrDao, VosECdr> implements VosECdrService {

    @Autowired
    private VosECdrDao vosECdrDao;

    @Autowired
    private Vos69ECdrDao vos69ECdrDao;

    @Override
    public List<VosECdr> getVosCDRByTimeAndTableName(String startTime, String endTime, String tableName) {

        if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime) || StringUtils.isEmpty(tableName)){
            return null;
        }
        return vosECdrDao.getVosCDRByTimeAndTableName(startTime,endTime,tableName);
    }

    @Override
    public List<VosECdr> getAllCdr() {
        return vos69ECdrDao.getAllCdr();
    }

    @Override
    public Double getTransferCost(String calleecallid, String tableName) {
        return vos69ECdrDao.getTransferCost(calleecallid,tableName);
    }
}
