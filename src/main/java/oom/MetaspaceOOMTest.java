package oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: MetaspaceOOMTest
 * @Description: TODO Metaspace元空间
 * @Author: Jiangxb
 * @Date: 2020/4/11 23:37
 * @Version 1.0
 * JVM参数
 *  -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=9m
 *
 *  Java 8及之后的版本使用Metaspace来替代永久代。
 *
 *  Metaspace是方法区在HotSpot中的实现，它与持久代最大的区别在于：Metaspace并不在
 *  虚拟机内存中而是使用本地内存也即在java8中，classe metadata(the virtual machines internal presentation of java class),
 *  被存储在叫做Metaspace的native memory
 *
 *  永久代(java8 后被元空间Metaspace取代了)存放了一下信息：
 *      虚拟机加载的类信息
 *      常量池
 *      静态变量
 *      即时编译后的代码
 *  模拟Metaspace空间溢出，我们不断生成类往元空间灌，类占据的空间总是会超过Metaspace指定的空间大小的
 *      报错：java.lang.OutOfMemoryError: Metaspace
 *
 *  补充知识点：Enhancer类的使用？
 *
 */
public class MetaspaceOOMTest {
    static class OOMTest{}
    public static void main(String[] args) {
        // 模拟计数多少次以后发生异常
        int i = 0;

        try {
            while (true){
                i++;
                //创建Enhancer增强代理类
                Enhancer enhancer = new Enhancer();
                //设置代理目标
                enhancer.setSuperclass(OOMTest.class);
                //不使用缓存
                enhancer.setUseCache(false);
                //设置单一回调对象，在调用中拦截对目标方法的调用
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                //使用create()制作代理对象
                enhancer.create();
            }
        } catch (Throwable e){
            System.out.println("***************多少次后发生异常：" + i);
            e.printStackTrace();
        }
    }
}
