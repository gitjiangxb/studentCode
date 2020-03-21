package queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ProdConsumer_TraditionDemo
 * @Description: TODO 传统版 生产者消费者模式
 * @Author: Jiangxb
 * @Date: 2020/3/21 15:18
 * @Version 1.0
 *
 * 多线程企业级模板口诀：
 *  高内聚低耦合的情况下线程 操作 资源类
 *  判断 干活 唤醒通知
 *  严防多线程情况下的虚假唤醒
 *
 *  题目：
 *      一个初始值为0的变量，两个线程对其交替操作，一个加1 一个减1，来5轮
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        /**
         * 验证线程的判断为什么只能使用while
         *  修改资源类的判断为 if，然后复制下面一组(加减)线程操作，运行查看结果
         */
        new Thread(()->{
            for (int i = 1; i <= 5; i++){
                shareData.addOperation();
            }
        },"AAA").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++){
                shareData.reduceOperation();
            }
        },"BBB").start();
    }
}

/**
 * 资源类
 */
class ShareData{
    private Integer number = 0;
    // 非公平锁(默认构造函数) 可重入锁
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 加操作
     */
    public void addOperation(){
        lock.lock();
        try {
            // 1、判断：多线程的判断不能使用if
            while (number != 0){
                // 2、线程等待,不能干活
                condition.await();
            }
            // 3、做具体的事情
            {
                number++;
                System.out.println(Thread.currentThread().getName()
                        + "\t" + number);
            }
            // 4、线程唤醒
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 减操作
     */
    public void reduceOperation(){
        lock.lock();
        try {
            // 1、判断
            while (number == 0){
                // 2、线程等待,不能干活
                condition.await();
            }
            // 3、做具体的事情
            {
                number--;
                System.out.println(Thread.currentThread().getName()
                        + "\t" + number);
            }

            // 4、线程唤醒
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
