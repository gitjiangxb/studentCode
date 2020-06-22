package com.batch;

import batch.BatchApplication;
import batch.service.JobLauncherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BatchApplication.class})
public class BatchTest {

    @Autowired
    private JobLauncherService jobLauncherService;

    @Autowired
    private Job customerDtoJob;

    @Autowired
    private Job smsDailyJob;

    @Autowired
    private Job voiceTypeTJob;

    @Test
    public void getCustomerJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        Map<String, Object> jobResult = jobLauncherService.startJob(customerDtoJob, jobParameters);
        System.out.println(jobResult);
    }

    @Test
    public void testSmsDay() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("calc_day", "2020-02-26")
                .toJobParameters();

        Map<String, Object> jobResult = jobLauncherService.startJob(smsDailyJob, jobParameters);
        System.out.println(jobResult);
    }

    @Test
    public void testVoiceDayReportJob() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("voice_sms_app_id","1118")
                .toJobParameters();

        Map<String, Object> stringObjectMap = jobLauncherService.startJob(voiceTypeTJob, jobParameters);
        System.out.println(stringObjectMap);
    }
}
