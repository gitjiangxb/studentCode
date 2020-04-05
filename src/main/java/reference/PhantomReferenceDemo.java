package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: PhantomReferenceDemo
 * @Description: TODO 虚引用
 * @Author: Jiangxb
 * @Date: 2020/4/5 21:11
 * @Version 1.0
 * 引用队列的使用 有点类似 Spring的后置处理器
 * (回收后需要做的事情)
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get()); // 打印：null(虚引用 get方法总是返回null)
        System.out.println(referenceQueue.poll());

        System.out.println("----------------");

        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(phantomReference.get()); // 打印：null(虚引用 get方法总是返回null)
        System.out.println(referenceQueue.poll());  // 打印：对象引用地址（垃圾回收后放入引用队列）
    }
}
