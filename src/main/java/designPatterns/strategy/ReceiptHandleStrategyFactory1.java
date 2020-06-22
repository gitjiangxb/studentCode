package designPatterns.strategy;

import designPatterns.strategy.impl.Mt1101ReceiptHandleStrategy;
import designPatterns.strategy.impl.Mt2101ReceiptHandleStrategy;
import designPatterns.strategy.impl.Mt8104ReceiptHandleStrategy;
import designPatterns.strategy.impl.Mt9999ReceiptHandleStrategy;

/**
 * @ClassName: ReceiptHandleStrategyFactory1
 * @Description: TODO  回执处理策略的 策略工厂【传统写法】
 * @Author: Jiangxb
 * @Date: 2020/06/22 10:24
 * @Version 1.0
 */
public class ReceiptHandleStrategyFactory1 {
    private ReceiptHandleStrategyFactory1(){}

    public static IReceiptHandleStrategy getReceiptHandleStrategy(String receiptType){
        IReceiptHandleStrategy receiptHandleStrategy = null;

        if ("MT2101".equals(receiptType)){
            receiptHandleStrategy = new Mt2101ReceiptHandleStrategy();
        } else if ("MT1101".equals(receiptType)){
            receiptHandleStrategy = new Mt1101ReceiptHandleStrategy();
        } else if ("MT8104".equals(receiptType)){
            receiptHandleStrategy = new Mt8104ReceiptHandleStrategy();
        } else if ("MT9999".equals(receiptType)){
            receiptHandleStrategy = new Mt9999ReceiptHandleStrategy();
        }

        return receiptHandleStrategy;
    }
}
