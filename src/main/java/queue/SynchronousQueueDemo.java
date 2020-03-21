package queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SynchronousQueueDemo
 * @Description: TODO SynchronousQueue
 * @Author: Jiangxb
 * @Date: 2020/3/19 15:13
 * @Version 1.0
 * SynchronousQueue没有容量
 * 与其他BlockingQueue不同,SynchronousQueue是一个不存储元素的BlockingQueue.
 * 每一个put操作必须等待一个take操作,否则不能继续添加元素,反之亦然.
 */
public class SynchronousQueueDemo {
    // 阻塞队列SynchronousQueue演示
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        /**
         * 开两个线程,一个线程负责写,一个线程负责读
         *
         * 注意：类似于消息队列，生产一个消费一个
         */
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t take " + blockingQueue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
