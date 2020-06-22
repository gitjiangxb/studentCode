package designPatterns.strategy;

import java.util.List;

/**
 * @ClassName: StrategyPatternDemo
 * @Description: TODO 策略模式 —— 干掉if-else
 * @Author: Jiangxb
 * @Date: 2020/06/22 09:32
 * @Version 1.0
 *
 * 示例说明：根据不同的回执类型处理不同的回执信息
 *
 * 策略模式说明：
 *   策略模式是对算法的包装，是把使用算法的责任和算法本身分割开来，委派给不同的对象管理，最终可以实现多重if判断问题(多重if判断的逻辑在策略工厂中实现)
 *   1、环境(Context)角色：持有一个 Strategy的引用。（从策略工厂中获取具体的策略）
 *   2、抽象策略(Strategy)角色：是一个抽象角色，通常由一个接口或者抽象类实现。
 *   3、具体策略(ConcreteStrategy)角色：实现了相关的算法或者行为(实现/继承 抽象策略角色)
 *
 *   补充：其实还有一个策略工厂的角色：把所有的具体策略集中到一起，对外提供一个根据类型获取具体的策略的方法
 *
 */
public class StrategyPatternDemo {
    public static void main(String[] args) {
//        Demo1();
        Demo2();
    }

    /**
     * 策略模式+简单工厂
     *      由于我们的目的是消除if-else，那么这里需要将ReceiptHandleStrategyFactory1策略工厂进行改造下，
     *  采用字典的方式存放我的策略，而Map具备key-value结构，采用Map是个不错选择。
     */
    public static void Demo1(){
        // 模拟回执
        List<ReceiptBean> receiptBeanList = ReceiptBuilder.getReceiptBeanList();

        // 策略上下文（维护一个策略接口的引用）
        ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext();

        for (ReceiptBean receiptBean : receiptBeanList){
            // 获取并设置策略（根据类型从工厂中去获取具体实现类）
            IReceiptHandleStrategy receiptHandleStrategy = ReceiptHandleStrategyFactory1.getReceiptHandleStrategy(receiptBean.getType());
            receiptStrategyContext.setReceiptHandleStrategy(receiptHandleStrategy);

            //执行策略
            receiptStrategyContext.handleReceipt(receiptBean);
        }
    }

    /**
     * 策略模式+简单工厂
     *      我们已经消除了if-else的结构，每当新来了一种回执，只需要添加新的回执处理策略，
     *  并修改ReceiptHandleStrategyFactory2中的Map集合。
     */
    public static void Demo2(){
        // 模拟回执
        List<ReceiptBean> receiptBeanList = ReceiptBuilder.getReceiptBeanList();

        // 策略上下文
        ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext();

        for (ReceiptBean receiptBean : receiptBeanList){
            // 获取并设置策略
            IReceiptHandleStrategy receiptHandleStrategy = ReceiptHandleStrategyFactory2.getReceiptHandleStrategy(receiptBean.getType());
            receiptStrategyContext.setReceiptHandleStrategy(receiptHandleStrategy);

            //执行策略
            receiptStrategyContext.handleReceipt(receiptBean);
        }
    }

// TODO 目前上面的做法每新增一个回执类型（需要实现一个新的回执类），就需要去修改策略工厂里面的代码【改动量大，不适合；应该改为通过反射将实现了接口的实现类扫描进去 / 通过注解】
}
