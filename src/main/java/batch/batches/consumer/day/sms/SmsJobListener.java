package batch.batches.consumer.day.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Component
public class SmsJobListener extends JobExecutionListenerSupport {

    @Autowired
    @Qualifier("financeDatasource")
    DataSource financeDatasource;

    private static final Logger log = LoggerFactory.getLogger(SmsJobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String sql = " delete from customer_day_report where calc_day=? and business_type=? ";
        try (Connection connection = financeDatasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, jobExecution.getJobParameters().getString("calc_day"));
            statement.setInt(2, 101);
            statement.execute();
            log.info(sql);
        } catch (Exception e) {
            log.error("同步customer失败", e);
            throw new RuntimeException(e);
        }

        super.beforeJob(jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
        }
    }
}
