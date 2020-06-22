package batch.batches.multistep;

import batch.batches.consumer.day.ConsumerDayReport;
import batch.batches.multistep.bean.VoiceVernotMessage;
import org.springframework.batch.item.ItemProcessor;

/**
 * 语音日报表处理——语音通知——数据处理
 * @author jiangxb
 * @Date 2020-06-20 10:15:45
 * 将数据源（语音通知表） ——> 目标表（语音日报表） 之间的数据转换处理
 */
public class VoiceTypeTProcessor implements ItemProcessor<VoiceVernotMessage, ConsumerDayReport> {
    @Override
    public ConsumerDayReport process(VoiceVernotMessage item) throws Exception {
        ConsumerDayReport consumerDayReport = new ConsumerDayReport();
        consumerDayReport.setCustomerId(item.getCustomerId());
        consumerDayReport.setCustomerAppId(item.getVoiceSmsAppId());
        consumerDayReport.setBusinessType(item.getVoiceType());
        return consumerDayReport;
    }
}
