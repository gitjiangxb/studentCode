package designPatterns.chainOfResponsibility;


import java.util.List;

/**
 * @ClassName: ChainOfResponsibilityDemo
 * @Description: TODO 责任链模式示例
 * @Author: Jiangxb
 * @Date: 2020/06/22 14:25
 * @Version 1.0
 *
 * 示例说明：根据不同的回执类型处理不同的回执信息
 *
 * 责任链（Chain of Responsibility）模式：责任链模式也叫职责链模式，为了避免请求发送者与多个请求处理者耦合在一起，将所有请求的处理者通过前一对象
 *      记住其下一个对象的引用而连成一条链；当有请求发送时，可将请求沿着这条链传递，直到有对象处理它为止。
 *
 *  1、抽象处理者（Handler）角色：定义一个处理请求的接口，包含抽象处理方法和 一个后续连接
 *  2、具体处理者（Concrete Handler）角色：实现抽象处理者的处理方法，判断是否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者
 *  3、客户类（Client）角色：创建处理链，并向链头的具体处理者对象提交请求，它不关心处理细节和请求的传递过程
 *
 */
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // 模拟回执
        List<ReceiptBean> receiptBeanList = ReceiptBuilder.getReceiptBeanList();

        for (ReceiptBean receiptBean : receiptBeanList){
            // 创建处理链
            ReceiptHandleChain receiptHandleChain = new ReceiptHandleChain();
            // 回执处理链对象
            receiptHandleChain.handlerReceipt(receiptBean);
        }
    }
}
