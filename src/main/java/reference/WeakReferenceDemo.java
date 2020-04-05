package reference;

import java.lang.ref.WeakReference;

/**
 * @ClassName: WeakReferenceDemo
 * @Description: TODO 弱引用
 * @Author: Jiangxb
 * @Date: 2020/4/5 10:12
 * @Version 1.0
 * 只要垃圾回收机制一运行，不管JVM的内存空间是否足够，都会回收该对象占用的内存
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(o1);

        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();

        System.out.println("---------------");

        System.out.println(o1);     // 打印：null
        System.out.println(weakReference.get());    // 打印：null
    }
}
