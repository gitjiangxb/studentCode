package batch.batches.customer;

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
public class CustomerJobListener extends JobExecutionListenerSupport {

    @Autowired
    @Qualifier("financeDatasource")
    DataSource financeDatasource;

    private static final Logger log = LoggerFactory.getLogger(CustomerJobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String sql = " truncate table customer ";
        try (Connection connection = financeDatasource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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
