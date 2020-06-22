package batch.batches.multistep;


import batch.batches.consumer.day.ConsumerDayReport;
import batch.batches.multistep.bean.VoiceVernotMessage;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 语音日报表处理 —— 语音通知(voiceType = 2)
 * @author jiangxb
 * @Date 2020-06-20 10:15:45
 *
 * 一个Step 包括：Reader、Processor、Writer
 *
 * 语音通知单条保存的表为：VoiceVernotMessage
 */
@EnableBatchProcessing
@Configuration
public class VoiceType2Batch {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step voiceTypeTStep(
//            ItemReader voiceTypeTReader,
            ItemReader voiceTypeTPageReader,
            ItemProcessor voiceTypeTProcessor,
            ItemWriter voiceTypeTWrite
    ){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .chunk(100)
//                .reader(voiceTypeTReader)
                .reader(voiceTypeTPageReader)
                .processor(voiceTypeTProcessor)
                .writer(voiceTypeTWrite)
                .build();
    }

    @Bean
    @StepScope  // 步骤范围（如果需要获取设置的参数，必加这个注解）
    public JdbcCursorItemReader<VoiceVernotMessage> voiceTypeTReader(
            @Qualifier("nxcloudDataSource") DataSource nxcloudDataSource,
            @Value("#{jobParameters}") Map<String,Object> jobParameters     // 参数名 与 启动步骤传递进来的名称保持一致
    ){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String voice_sms_app_id = jobParameters.get("voice_sms_app_id").toString();
        String sql = "select * from voice_vernot_message where voice_sms_app_id = " + voice_sms_app_id;
        return new JdbcCursorItemReaderBuilder<VoiceVernotMessage>().name(funcName)
                .dataSource(nxcloudDataSource)
                .sql(sql)
                .verifyCursorPosition(false)
                .rowMapper(BeanPropertyRowMapper.newInstance(VoiceVernotMessage.class))
                .build();
    }


    /**
     * 对读取到的数据进行处理，然后传递给输出
     *      特别是不同数据表对象时，这个就非常重要了
     * @return
     */
    @Bean
    public ItemProcessor voiceTypeTProcessor(){
        return new VoiceTypeTProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<ConsumerDayReport> voiceTypeTWrite(@Qualifier("financeDatasource") DataSource financeDatasource){
        String inserSql = "INSERT INTO customer_day_report_test(customer_id,customer_app_id,business_type) " +
                "VALUES (:customerId,:customerAppId,:businessType)";
        return new JdbcBatchItemWriterBuilder<ConsumerDayReport>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(inserSql)
                .dataSource(financeDatasource)
                .build();
    }


// ----------------------- TODO 分页读数据进行处理 ---------------------------------------


    @Bean
    @StepScope
    public JdbcPagingItemReader<VoiceVernotMessage> voiceTypeTPageReader(
            @Qualifier("nxcloudDataSource") DataSource nxcloudDataSource,
            @Qualifier("queryProvider") PagingQueryProvider queryProvider
    ){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return new JdbcPagingItemReaderBuilder<VoiceVernotMessage>().name(funcName)
                .dataSource(nxcloudDataSource)
                .queryProvider(queryProvider)
                .parameterValues(new HashMap<>())
                .rowMapper(BeanPropertyRowMapper.newInstance(VoiceVernotMessage.class))
                .pageSize(2)
                .build();
    }


    @Bean
    @StepScope
    public SqlPagingQueryProviderFactoryBean queryProvider( @Qualifier("nxcloudDataSource") DataSource nxcloudDataSource,
                                                            @Value("#{jobParameters}") Map<String,Object> jobParameters) {
        SqlPagingQueryProviderFactoryBean provider = new SqlPagingQueryProviderFactoryBean();

        String voice_sms_app_id = jobParameters.get("voice_sms_app_id").toString();

        provider.setDataSource(nxcloudDataSource);
        provider.setSelectClause("*");
        provider.setFromClause("voice_vernot_message");
        provider.setWhereClause("voice_sms_app_id = " + voice_sms_app_id );
        /**
         * 在sortKey上必须使用unique key constraint约束，因为只有这样才能得以确保执行之间不会丢失任何数据。
         *  这也可以说是JdbcCursorItemReader相对便利的一点优势。
         */
        provider.setSortKey("id");

        return provider;
    }

}
