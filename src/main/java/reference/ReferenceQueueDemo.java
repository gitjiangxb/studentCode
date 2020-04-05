package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ReferenceQueueDemo
 * @Description: TODO 引用队列
 * @Author: Jiangxb
 * @Date: 2020/4/5 21:02
 * @Version 1.0
 * 被回收前会被引用队列保存下
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<Object>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());  // 打印：null

        System.out.println("--------------");

        o1 = null;
        System.gc();
        TimeUnit.MICROSECONDS.sleep(500);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());  // 打印：对象引用地址(垃圾回收启动过)
    }
}
