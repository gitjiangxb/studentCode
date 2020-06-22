package batch.batches.multistep;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

/**
 * 语音日报表处理 —— 语音群呼(voiceType = 0)
 * @author jiangxb
 * @Date 2020-06-20 16:31:45
 *
 * 一个Step 包括：Reader、Processor、Writer
 *
 * 语音群呼单条保存的表为：VoiceGroupMessage
 */
@EnableBatchProcessing
@Configuration
public class VoiceType0Batch {

}
