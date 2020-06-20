package batch.batches.customer;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class CustomerJob  {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job customerDtoJob(Step db2DbStep) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(customerJobListener)
                .flow(db2DbStep)
                .end()
                .build();
    }

    @Bean
    public Step db2DbStep(ItemReader db2DbItemReader, ItemProcessor db2DbProcessor
            , ItemWriter db2DbWriter) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .<Customer, Customer>chunk(1000)
                .reader(db2DbItemReader)
                .processor(db2DbProcessor)
                .writer(db2DbWriter)
                .build();
    }

    @Bean
    public ItemReader db2DbItemReader(@Qualifier("nxcloudDataSource") DataSource nxcloudDataSource) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String readSql = " select * from customer where id>2";
        return new JdbcCursorItemReaderBuilder<Customer>().name(funcName)
                .dataSource(nxcloudDataSource)
                .sql(readSql)
                .verifyCursorPosition(false)
                .rowMapper(BeanPropertyRowMapper.newInstance(Customer.class))
                .build();
    }

    @Bean
    public ItemWriter db2DbWriter(@Qualifier("financeDatasource") DataSource financeDatasource) {
        String inserSql = "INSERT INTO customer(id,new_code,cus_code,sales_id,name) " +
                "VALUES (:id,:newCode,:cusCode,:salesId,:name)";
        return new JdbcBatchItemWriterBuilder<Customer>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(inserSql)
                .dataSource(financeDatasource)
                .build();
    }

    @Bean
    public ItemProcessor db2DbProcessor() {
        return new CustomerItemProcessor();
    }

    @Autowired
    CustomerJobListener customerJobListener;
}
