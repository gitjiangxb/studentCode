package designPatterns.strategy.impl;

import designPatterns.strategy.IReceiptHandleStrategy;
import designPatterns.strategy.ReceiptBean;

/**
 * @ClassName: Mt1101ReceiptHandleStrategy
 * @Description: TODO  类型为“Mt1101”回执处理实现类
 * @Author: Jiangxb
 * @Date: 2020/06/22 10:22
 * @Version 1.0
 */
public class Mt1101ReceiptHandleStrategy implements IReceiptHandleStrategy {
    @Override
    public void handleReceipt(ReceiptBean receiptBean) {
        System.out.println("解析类型为Mt1101的回执信息：" + receiptBean.getMessage());
    }
}
