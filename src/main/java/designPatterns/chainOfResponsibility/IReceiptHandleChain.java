package designPatterns.chainOfResponsibility;

/**
 * @ClassName: IReceiptHandleChain
 * @Description: TODO 责任链接口
 * @Author: Jiangxb
 * @Date: 2020/06/22 14:29
 * @Version 1.0
 */
public interface IReceiptHandleChain {
    void handlerReceipt(ReceiptBean receiptBean);
}
