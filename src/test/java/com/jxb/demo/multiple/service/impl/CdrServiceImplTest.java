package com.jxb.demo.multiple.service.impl;


import com.jxb.demo.multiple.entity.Cdr;
import com.jxb.demo.multiple.entity.VosECdr;
import com.jxb.demo.multiple.vos69Dao.Vos69ECdrDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class CdrServiceImplTest {

    @Autowired
    private CdrServiceImpl cdrService;

    @Autowired
    private VosECdrServiceImpl vosECdrService;

    @Autowired
    private Vos69ECdrDao vos69ECdrDao;

    @Test
    public void test(){
        Cdr byId = cdrService.getById("1");
    }

    @Test
    public void vosTest(){
//        vosECdrService.getById("110");
        vosECdrService.getAllCdr();
    }

    @Test
    public void vos69Test(){
        vos69ECdrDao.selectById("110");
    }
}