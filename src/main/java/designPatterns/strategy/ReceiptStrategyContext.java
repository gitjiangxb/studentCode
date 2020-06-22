package designPatterns.strategy;

/**
 * @ClassName: ReceiptStrategyContext
 * @Description: TODO  回执策略的上下文（策略接口的持有者）
 * @Author: Jiangxb
 * @Date: 2020/06/22 10:24
 * @Version 1.0
 */
public class ReceiptStrategyContext {
    private IReceiptHandleStrategy receiptHandleStrategy;

    /**
     * 设置策略接口
     * @param receiptHandleStrategy
     */
    public void setReceiptHandleStrategy(IReceiptHandleStrategy receiptHandleStrategy){
        this.receiptHandleStrategy = receiptHandleStrategy;
    }

    /**
     * 执行策略接口方法
     * @param receiptBean
     */
    public void handleReceipt(ReceiptBean receiptBean){
        if (receiptHandleStrategy != null){
            receiptHandleStrategy.handleReceipt(receiptBean);
        }
    }
}
