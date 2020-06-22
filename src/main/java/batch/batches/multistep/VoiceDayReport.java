package batch.batches.multistep;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 语音日报表处理
 * @author jiangxb
 * @Date 2020-06-20 10:15:45
 *
 * 一个job 多个步骤
 */
@EnableBatchProcessing
@Configuration
public class VoiceDayReport {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job voiceTypeTJob(Step voiceTypeTStep){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
//                .listener(customerJobListener)
                .flow(voiceTypeTStep)
                .end()
                .build();
    }


}
