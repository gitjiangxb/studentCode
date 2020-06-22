package designPatterns.chainOfResponsibility;

import java.util.List;

/**
 * @ClassName: ReceiptHandleChain
 * @Description: TODO 责任链接口 实现类
 * @Author: Jiangxb
 * @Date: 2020/06/22 14:32
 * @Version 1.0
 */
public class ReceiptHandleChain implements IReceiptHandleChain {

    // 记录当前处理者的位置
    private int index = 0;

    // 处理者集合
    private static List<IReceiptHandler> receiptHandlerList;

    static {
        /**
         * 从容器中获取处理者容器对象【手动添加到容器中】
         *  这种形式如果新增一个回执类型，需要添加一个新的回执处理器，然后需要在 处理者容器 中 手动 将刚刚创建的回执处理器添加到容器中
         */
//        receiptHandlerList = ReceiptHandlerContainer.getReceiptHandlerList();

        /**
         * 从容器中获取处理者容器对象【包扫描形式】
         *      这种形式完美符合了开闭原则，如果新增一个回执类型，只需要添加一个新的回执处理器即可，无需做其它改动
         */
        receiptHandlerList = ReceiptHandlerContainer.getReceiptHandlerList2();
    }

    @Override
    public void handlerReceipt(ReceiptBean receiptBean) {
        if (receiptHandlerList != null && receiptHandlerList.size() > 0 ){
            if (index != receiptHandlerList.size()){
                IReceiptHandler receiptHandler = receiptHandlerList.get(index++);
                receiptHandler.handlerReceipt(receiptBean,this);
            }
        }
    }
}
