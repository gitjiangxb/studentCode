package com.jxb.demo.multiple.task;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.jxb.demo.multiple.dao.CdrDao;
import com.jxb.demo.multiple.entity.VosECdr;
import com.jxb.demo.multiple.service.impl.VosECdrServiceImpl;
import com.jxb.demo.multiple.vos69Dao.Vos69ECdrDao;
import com.jxb.demo.multiple.vosDao.VosECdrDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务从VOS端拉取话单的CDR信息
 * @Author: Jiangxb
 * @Date: 2020/08/03 11:18
 *
 * TODO 注意测试号码需要单独处理【未写逻辑】
 */
@Component
public class PullVosCDRTask {

    private static Logger logger = LoggerFactory.getLogger(PullVosCDRTask.class);

    private static final String TABLE_NAME = "e_cdr_";

    @Autowired
    private VosECdrServiceImpl vosECdrService;

    @Autowired
    private CdrDao cdrDao;

    @Autowired
    private VosECdrDao vosECdrDao;

    @Autowired
    private Vos69ECdrDao vos69ECdrDao;


//    @Scheduled(cron = "0 0/5 * * * ? ")
    @Scheduled(cron = "0/20 * * * * ?")
    public void getVosCDR(){
        logger.info("开始执行定时任务：每五分钟去VOS里面拉取CDR");
        String yyyyMMdd = DateUtil.format(DateUtil.date(), "yyyyMMdd");
        String tableName = TABLE_NAME + yyyyMMdd;

        DateTime endTime = DateUtil.parse(DateUtil.now());
        DateTime startTime = DateUtil.parse(DateUtil.now()).offset(DateField.MINUTE, -20);

//        List<VosECdr> vosCDRByTimeAndTableName = vosECdrService.getVosCDRByTimeAndTableName(String.valueOf(startTime.getTime()), String.valueOf(endTime.getTime()),tableName);
        List<VosECdr> vosCDRByTimeAndTableName = vosECdrService.getVosCDRByTimeAndTableName("1596492849000", "1596535845000", "e_cdr_20200804");
        if (vosCDRByTimeAndTableName != null && vosCDRByTimeAndTableName.size() != 0){
            logger.info("每五分钟去表{}，拉取{}到{}之间的数据,总共拉取了{}条数据",tableName,startTime,endTime,vosCDRByTimeAndTableName.size());
        } else {
            logger.info("每五分钟去表{}，拉取{}到{}之间的数据,没有拉取到数据",tableName,startTime,endTime);
        }
        logger.info("========定时任务执行完成：每五分钟去VOS里面拉取CDR========");
    }



}
