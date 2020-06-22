package designPatterns.chainOfResponsibility;

/**
 * @ClassName: IReceiptHandler
 * @Description: TODO 回执处理者接口
 * @Author: Jiangxb
 * @Date: 2020/06/22 14:25
 * @Version 1.0
 */
public interface IReceiptHandler {
    /**
     * 回执处理方法
     * @param receiptBean   回执类型Bean
     * @param handleChain   责任链对象（下一个链路）  ———— 这点与策略模式有所不同
     */
    void handlerReceipt(ReceiptBean receiptBean,IReceiptHandleChain handleChain);
}
