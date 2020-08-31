package com.jxb.demo.multiple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxb.demo.multiple.dao.CdrDao;
import com.jxb.demo.multiple.entity.Cdr;
import com.jxb.demo.multiple.service.CdrService;
import org.springframework.stereotype.Service;

@Service
public class CdrServiceImpl extends ServiceImpl<CdrDao, Cdr> implements CdrService {
}
