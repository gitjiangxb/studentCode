package batch.batches.consumer.day.sms;

import batch.batches.consumer.day.ConsumerDayReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class SmsProcessor implements ItemProcessor<ConsumerDayReport, ConsumerDayReport> {

    private static final Logger log = LoggerFactory.getLogger(SmsProcessor.class);

    @Override
    public ConsumerDayReport process(final ConsumerDayReport report) throws Exception {
        report.setBusinessType(101);
        return report;
    }

}
