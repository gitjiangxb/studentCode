package designPatterns.chainOfResponsibility;

import com.sun.deploy.net.MessageHeader;
import designPatterns.chainOfResponsibility.impl.Mt2101ReceiptHandler;
import designPatterns.chainOfResponsibility.impl.Mt8104ReceiptHandler;
import designPatterns.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: ReceiptHandlerContainer
 * @Description: TODO 处理者容器
 * @Author: Jiangxb
 * @Date: 2020/06/22 14:32
 * @Version 1.0
 */
public class ReceiptHandlerContainer {

    private ReceiptHandlerContainer(){}

    /**
     * 将回执处理者添加到容器中
     * @return
     */
    public static List<IReceiptHandler> getReceiptHandlerList(){
        List<IReceiptHandler> receiptHandlerList = new ArrayList<>();
        receiptHandlerList.add(new Mt2101ReceiptHandler());
        receiptHandlerList.add(new Mt8104ReceiptHandler());
        return receiptHandlerList;
    }

    /**
     * 利用包扫描的形式将实现了【回执处理者接口】的实现类添加到容器中
     * @return
     */
    public static List<IReceiptHandler> getReceiptHandlerList2(){
        List<IReceiptHandler> receiptHandlerList = new ArrayList<>();

        //获取IReceiptHandler接口的实现类
        Set<Class<?>> classSet = ReflectionUtil.getClassSetBySuper(IReceiptHandler.class);
        if (classSet != null && classSet.size() > 0){
            for (Class<?> cla : classSet){
                try {
                    receiptHandlerList.add((IReceiptHandler) cla.newInstance());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return receiptHandlerList;
    }
}
