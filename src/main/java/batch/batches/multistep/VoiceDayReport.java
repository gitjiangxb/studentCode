package batch.batches.multistep;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public Job voiceTypeTJob(@Qualifier("voiceTypeTStep")Step voiceTypeTStep){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
//                .listener(customerJobListener)
                .flow(voiceTypeTStep)
                .end()
                .build();
    }

    /*
    一个job 多个step的示例
    @Bean
    public Job smsDayReportJob(
            @Qualifier("smsDayReportStep") Step smsDayReportStep,
            @Qualifier("agentSmsDayReportStep") Step agentSmsDayReportStep,
            @Qualifier("customerSmsDayStep") Step customerSmsDayStep,
            @Qualifier("vendorSmsDayStep") Step vendorSmsDayStep
    ) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Flow smsNoAgentFlow = new FlowBuilder<SimpleFlow>("smsNoAgentFlow")
                .start(smsDayReportStep)
                .build();
        Flow smsAgentFlow = new FlowBuilder<SimpleFlow>("smsAgentFlow")
                .start(agentSmsDayReportStep)
                .build();
        Flow smsFlow = new FlowBuilder<SimpleFlow>("smsFlow")
                .start(smsNoAgentFlow)
                .split(new SimpleAsyncTaskExecutor())
                .add(smsAgentFlow)
                .build();
        return jobBuilderFactory.get(funcName)
                .listener(smsDayReportJobListener)
                .start(smsFlow)
                .next(customerSmsDayStep)
                .next(vendorSmsDayStep)
                .end()
                .build();
    }
    */

}
