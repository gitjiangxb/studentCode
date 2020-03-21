package queue;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: BlockingQueueDemo
 * @Description: TODO 阻塞队列
 * @Author: Jiangxb
 * @Date: 2020/3/18 15:53
 * @Version 1.0
 *  ArrayBlockingQueue：
 *      是一个基于数据结构的有界阻塞队列，此队列按FIFO(先进先出)
 *   原则对元素进行排序
 *  LinkedBlockingQueue：
 *      是一个基于链表结构的阻塞队列，此队列按FIFO（先进先出）排序元素，
 *   吞吐量通常要高于ArrayBlockingQueue。
 *  SynchronousQueue：
 *      一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除
 *   操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue
 *
 * 1、队列
 * 2、阻塞队列
 *  2.1、阻塞队列有没有好的一面
 *      比如：火锅店排队
 *  2.2、不得不阻塞，你如何管理
 *      比如：银行排队、医院抽血排队
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
//        throwException(blockingQueue);
//        specialValue(blockingQueue);
//        blocking(blockingQueue);
        blockingTimeOut(blockingQueue);
    }
    /**
     * BlockingQueue 阻塞队列API 之 抛出异常组
     */
    private static void throwException(BlockingQueue<String> blockingQueue){
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // 再插入就会报：Queue full
//        System.out.println(blockingQueue.add("x"));

        System.out.println("检查队头：" + blockingQueue.element());
        // 无元素取的时候报：NoSuchElementException 异常
        System.out.println("移除队头：" + blockingQueue.remove());
        System.out.println("检查队头：" + blockingQueue.element());
        System.out.println(blockingQueue.add("x"));
    }

    /**
     * BlockingQueue 阻塞队列API 之 特殊值
     */
    private static void specialValue(BlockingQueue<String> blockingQueue){
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // 插入失败返回false，不会抛异常
        System.out.println(blockingQueue.offer("x"));

        System.out.println("检查队头：" + blockingQueue.peek());
        // 无元素取的时候，返回null
        System.out.println("移除队头：" + blockingQueue.poll());
        System.out.println("检查队头：" + blockingQueue.peek());
    }

    /**
     * BlockingQueue 阻塞队列API 之 阻塞
     * @param blockingQueue
     */
    private static void blocking(BlockingQueue<String> blockingQueue){
        try {
            blockingQueue.put("a");
            blockingQueue.put("b");
            blockingQueue.put("c");
            System.out.println("=======当已经插入三个后，再插入时，一直等待========");
//            blockingQueue.put("x");

            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println("=======当取出三个后，再继续取出时，一直等待========");
//            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * BlockingQueue 阻塞队列API 之 超时
     */
    private static void blockingTimeOut(BlockingQueue<String> blockingQueue){
        try {
            System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("b", 2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("c", 2, TimeUnit.SECONDS));
            // 已经插入三个后,再插入时会等待两秒,两秒过后返回 true/false
//            System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS));

            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
            // 已经取出三个后,再取出时会等待两秒,两秒过后存在值就返回具体的,否则返回 null
            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
