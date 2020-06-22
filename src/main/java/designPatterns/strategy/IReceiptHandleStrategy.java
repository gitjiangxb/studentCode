package designPatterns.strategy;

/**
 * @ClassName: IReceiptHandleStrategy
 * @Description: TODO  回执处理 策略接口
 * @Author: Jiangxb
 * @Date: 2020/06/22 10:14
 * @Version 1.0
 */
public interface IReceiptHandleStrategy {
    /**
     * 回执信息处理接口
     * @param receiptBean
     */
    void handleReceipt(ReceiptBean receiptBean);
}
