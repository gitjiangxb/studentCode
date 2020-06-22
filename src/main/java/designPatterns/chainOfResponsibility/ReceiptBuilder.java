package designPatterns.chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ReceiptBuilder
 * @Description: TODO  模拟一个回执生成器
 * @Author: Jiangxb
 * @Date: 2020/06/22 10:31
 * @Version 1.0
 */
public class ReceiptBuilder {
    /**
     * 模拟一堆回执对象
     * @return
     */
    public static List<ReceiptBean> getReceiptBeanList(){
        List<ReceiptBean> receiptBeanList = new ArrayList<>();
        receiptBeanList.add(new ReceiptBean("MT2101","我是MT2101回执喔"));
        receiptBeanList.add(new ReceiptBean("MT1101","我是MT1101回执喔"));
        receiptBeanList.add(new ReceiptBean("MT8104","我是MT8104回执喔"));
        receiptBeanList.add(new ReceiptBean("MT9999","我是MT9999回执喔"));

        return receiptBeanList;
    }

}
