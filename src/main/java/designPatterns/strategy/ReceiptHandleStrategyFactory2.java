package designPatterns.strategy;

import designPatterns.strategy.impl.Mt1101ReceiptHandleStrategy;
import designPatterns.strategy.impl.Mt2101ReceiptHandleStrategy;
import designPatterns.strategy.impl.Mt8104ReceiptHandleStrategy;
import designPatterns.strategy.impl.Mt9999ReceiptHandleStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ReceiptHandleStrategyFactory1
 * @Description: TODO  回执处理策略的 策略工厂【字典模式】
 * @Author: Jiangxb
 * @Date: 2020/06/22 10:48
 * @Version 1.0
 */
public class ReceiptHandleStrategyFactory2 {

    private static Map<String,IReceiptHandleStrategy> receiptHandleStrategyMap;

    private ReceiptHandleStrategyFactory2(){
        receiptHandleStrategyMap = new HashMap<>();
        receiptHandleStrategyMap.put("MT2101",new Mt2101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT1101",new Mt1101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT8104",new Mt8104ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT9999",new Mt9999ReceiptHandleStrategy());
    }

    public static IReceiptHandleStrategy getReceiptHandleStrategy(String receiptType){
        if (receiptHandleStrategyMap == null){
            new ReceiptHandleStrategyFactory2();
        }
        return receiptHandleStrategyMap.get(receiptType);
    }
}
