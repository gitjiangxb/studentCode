package designPatterns.chainOfResponsibility.impl;

import designPatterns.chainOfResponsibility.IReceiptHandleChain;
import designPatterns.chainOfResponsibility.IReceiptHandler;
import designPatterns.chainOfResponsibility.ReceiptBean;

/**
 * @ClassName: MtMT8104ReceiptHandler
 * @Description: TODO MT8104回执处理者
 * @Author: Jiangxb
 * @Date: 2020/06/22 14:40
 * @Version 1.0
 */
public class Mt8104ReceiptHandler implements IReceiptHandler {
    @Override
    public void handlerReceipt(ReceiptBean receiptBean, IReceiptHandleChain handleChain) {
        if ("MT8104".equals(receiptBean.getType())){
            System.out.println("解析报文MT8104:" + receiptBean.getMessage());
        }
        else
        {
            System.out.println("---------------在MT8104中处理不了：" + receiptBean.getType() + ",继续往下传递");
            // 处理不了改回执就往下传递
            handleChain.handlerReceipt(receiptBean);
        }
    }
}
