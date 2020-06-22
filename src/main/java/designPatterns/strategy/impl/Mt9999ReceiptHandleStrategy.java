package designPatterns.strategy.impl;

import designPatterns.strategy.IReceiptHandleStrategy;
import designPatterns.strategy.ReceiptBean;

/**
 * @ClassName: Mt9999ReceiptHandleStrategy
 * @Description: TODO  类型为“Mt9999”回执处理实现类
 * @Author: Jiangxb
 * @Date: 2020/06/22 10:22
 * @Version 1.0
 */
public class Mt9999ReceiptHandleStrategy implements IReceiptHandleStrategy {
    @Override
    public void handleReceipt(ReceiptBean receiptBean) {
        System.out.println("解析类型为Mt9999的回执信息：" + receiptBean.getMessage());
    }
}
