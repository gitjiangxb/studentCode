package designPatterns.strategy.impl;

import designPatterns.strategy.IReceiptHandleStrategy;
import designPatterns.strategy.ReceiptBean;

/**
 * @ClassName: Mt2101ReceiptHandleStrategy
 * @Description: TODO  类型为“Mt2101”回执处理实现类
 * @Author: Jiangxb
 * @Date: 2020/06/22 10:18
 * @Version 1.0
 */
public class Mt2101ReceiptHandleStrategy implements IReceiptHandleStrategy {
    @Override
    public void handleReceipt(ReceiptBean receiptBean) {
        System.out.println("解析类型为Mt2101的回执信息：" + receiptBean.getMessage());
    }
}
