package com.nxtele.did.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nxtele.did.dao.CdrDao;
import com.nxtele.did.entity.Cdr;
import org.springframework.stereotype.Service;

@Service
public class CdrServiceImpl extends ServiceImpl<CdrDao, Cdr> implements CdrService {
}
