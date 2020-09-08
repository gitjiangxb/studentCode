package com.nxtele.did.service;

import com.nxtele.did.entity.VosECdr;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VosECdrServiceTest {
    @Autowired
    private VosECdrServiceImpl vosECdrService;

    @Autowired
    private CdrServiceImpl cdrService;

    @Test
    public void vosTest(){
        cdrService.getById("1");
        List<VosECdr> allCdr = vosECdrService.getAllCdr();
        vosECdrService.getTransferCost("1596492849000","e_cdr_20200804");
        vosECdrService.getVosCDRByTimeAndTableName("1596492849000", "1596535845000", "e_cdr_20200804");
    }
}