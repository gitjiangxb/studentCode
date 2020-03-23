package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: ProdConsumer_BlockQueueDemo
 * @Description: TODO 阻塞队列版 生产者消费者模式
 * @Author: Jiangxb
 * @Date: 2020/3/23 0:47
 * @Version 1.0
 * 知识点总结使用：
 *  volatile / CAS / atomicInterge / BlockQueue / 线程交互 / 原子引用
 *
 * 小总结：
 *  ProdConsumer_TraditionDemo 与 ProdConsumer_BlockQueueDemo
 *
 *  ProdConsumer_TraditionDemo：
 *      在传统版里面我们必须去控制线程的细节(加锁、等待、唤醒等)，尤其还需要兼顾效率和线程安全
 *  ProdConsumer_BlockQueueDemo：
 *      在使用阻塞队列时，我们不需要关心什么时候阻塞线程，什么时候需要唤醒线程；因为这一切都交由BlockingQueue一手包办了。
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(3));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            System.out.println();
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"myProd").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            System.out.println();
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"myConsumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println(Thread.currentThread().getName() + "\t 五秒钟时间到，大老板main线程叫停生产，活动结束" );
        myResource.stop();
    }
}

/**
 * 线程操作资源类
 */
class MyResource{
    /**
     * 线程标识：默认为true，进行生产+消费
     * volatile： 使共享变量在线程间可见
     *
     * 注意：跟传统的生产者消费者对比，这里使没有使用Lock机制，而是借助于阻塞队列来完成
     */
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    // 这里不能限制死阻塞队列的具体实现类(至于具体需要使用什么阻塞队列，有使用的客户端来决定)
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception{
        String data = null;
        boolean retValue;

        while (FLAG){
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停了，表示FLAG=false，生产结束");
    }

    public void myConsumer() throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒钟没有取到值，消费退出");
                System.out.println();
                System.out.println();
                return; // 退出循环
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列" + result + "成功");
        }
    }

    public void stop(){
        this.FLAG = false;
    }
}
