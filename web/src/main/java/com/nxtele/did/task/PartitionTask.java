package com.nxtele.did.task;


import com.nxtele.did.dto.PartitionDto;
import com.nxtele.did.service.PartitionOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 分区表的定时任务
 * @Author: Jiangxb
 * @Date: 2020/06/30 13:53
 */
@Component
public class PartitionTask {
    private static final Logger logger = LoggerFactory.getLogger(PartitionTask.class);

    @Value("${dataBase_name}")
    private String dataBase_name;

    @Autowired
    private PartitionOperationService partitionOperationService;

    /**
     * 每天凌晨时,跑一次定时任务
     */
    @Scheduled(cron = "0 0 0 */1 * ?")
    public void messagePartitionTask(){
        logger.info("定时任务处理" + dataBase_name + ".did_cdr表的分区备份数据");
        PartitionDto partitionDto = new PartitionDto();
        partitionDto.setTableSchema(dataBase_name);
        partitionDto.setTableName("did_cdr");
        partitionDto.setIsBackup(false);
        partitionDto.setIsDelete(true);
        partitionDto.setDoesNotExistCreate(true);
        partitionDto.setPartitionField("gmt_create");
        partitionDto.setCreatePartitionNum(5);
        partitionOperationService.createPartition(partitionDto);
    }
}
