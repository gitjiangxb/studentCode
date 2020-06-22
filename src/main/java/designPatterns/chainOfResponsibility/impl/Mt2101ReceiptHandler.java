package designPatterns.chainOfResponsibility.impl;

import designPatterns.chainOfResponsibility.IReceiptHandleChain;
import designPatterns.chainOfResponsibility.IReceiptHandler;
import designPatterns.chainOfResponsibility.ReceiptBean;

/**
 * @ClassName: Mt2101ReceiptHandler
 * @Description: TODO MT2101回执处理者
 * @Author: Jiangxb
 * @Date: 2020/06/22 14:38
 * @Version 1.0
 */
public class Mt2101ReceiptHandler implements IReceiptHandler {
    @Override
    public void handlerReceipt(ReceiptBean receiptBean, IReceiptHandleChain handleChain) {
        if ("MT2101".equals(receiptBean.getType())){
            System.out.println("解析报文MT2101:" + receiptBean.getMessage());
        }
        else
        {
            System.out.println("---------------在MT2101中处理不了：" + receiptBean.getType() + ",继续往下传递");
            // 处理不了改回执就往下传递
            handleChain.handlerReceipt(receiptBean);
        }
    }
}
