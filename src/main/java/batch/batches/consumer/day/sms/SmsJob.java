package batch.batches.consumer.day.sms;

import batch.batches.consumer.day.ConsumerDayReport;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.Map;

@EnableBatchProcessing
@Configuration
public class SmsJob {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job smsDailyJob(Step smsDailyStep) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(customerJobListener)
                .flow(smsDailyStep)
                .end()
                .build();
    }

    @Bean
    public Step smsDailyStep(
            ItemReader smsDailyReader,
            ItemProcessor smsProcessor,
            ItemWriter smsDailyWriter
    ) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .<ConsumerDayReport, ConsumerDayReport>chunk(1000)
                .reader(smsDailyReader)
                .processor(smsProcessor)
                .writer(smsDailyWriter)
                .build();
    }

    @Bean
    @StepScope
    public JdbcCursorItemReader smsDailyReader(
            @Qualifier("nxcloudDataSource") DataSource nxcloudDataSource,
            @Value("#{jobParameters}") Map<String,Object> jobParameters
    ) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String calcDay = jobParameters.get("calc_day").toString();
        String readSql = "SELECT  " +
                " '" + calcDay + "' calc_day, " +
                "  customer_id, " +
                "  customer_app_id, " +
                "  SUM(customer_price * size) total_fee, " +
                "  SUM( " +
                "    (customer_price - vendor_price) * size " +
                "  ) total_profit  " +
                "FROM " +
                "  message_" + calcDay.replace("-", "") +
                " GROUP BY customer_id, " +
                "  customer_app_id";
        return new JdbcCursorItemReaderBuilder<ConsumerDayReport>()
                .name(funcName)
                .dataSource(nxcloudDataSource)
                .sql(readSql)
                .verifyCursorPosition(false)
                .rowMapper(BeanPropertyRowMapper.newInstance(ConsumerDayReport.class))
                .build();
    }

    @Bean
    public ItemWriter smsDailyWriter(@Qualifier("financeDatasource") DataSource financeDatasource) {
        String inserSql = "INSERT INTO customer_day_report_test(customer_id,customer_app_id,business_type,calc_day,total_fee,total_profit) " +
                "VALUES (:customerId,:customerAppId,:businessType,:calcDay,:totalFee,:totalProfit)";
        return new JdbcBatchItemWriterBuilder<ConsumerDayReport>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(inserSql)
                .dataSource(financeDatasource)
                .build();
    }

    @Bean
    public ItemProcessor smsProcessor() {
        return new SmsProcessor();
    }

    @Autowired
    SmsJobListener customerJobListener;
}
