package queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: SyncAndReentrantLockDemo
 * @Description: TODO synchronized 与 lock 有什么区别?
 * @Author: Jiangxb
 * @Date: 2020/3/21 21:53
 * @Version 1.0
 * 题目：synchronized 与 lock 有什么区别?用新的lock有什么好处？举例说明？
 *  1、原始构成
 *      synchronized是关键字属于JVM层面
 *          monitorenter（底层是通过monitor对象来完成，其实wait/notify等方法也依赖于monitor对象，只有在同步块和方法中才能调用wait/notify等方法）
 *          monitorexit
 *      lock是具体类（java.util.concurrent.locks.Lock）是API层面
 *  2、使用方法
 *      synchronized 不需要用户去手动释放锁，当synchronized代码执行完后系统会自动让线程释放对锁的占用
 *      ReentrantLock 则需要用户手动去释放若没有主动释放锁，就有可能导致出现死锁现象。
 *          需要lock() 和 unLock() 方法配合try/finally语句来完成
 *  3、等待是否可中断
 *      synchronized 不可中断，除非抛出异常或者正常运行完成
 *      ReentrantLock 可中断。
 *          ①、设置超时方法 tryLock(long timeout,TimeUnit unit)
 *          ②、lockInterruptibly()放代码块中，调用interrupt()方法可中断
 *  4、加锁是否公平
 *      synchronized 非公平锁
 *      ReentrantLock 两者都可以，默认非公平锁；构造函数可以传入boolean值，true为公平锁，false为非公平锁
 *  5、锁绑定多个条件Condition
 *      synchronized 没有
 *      ReentrantLock 用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程
 *
 *==========================================================================================
 *  题目：多线程之间按顺序调用，实现A -> B -> C三个线程启动，需要如下：
 *      AA打印5次，BB打印10次，CC打印15次
 *      紧接着
 *      AA打印5次，BB打印10次，CC打印15次
 *      。。。。。。
 *      来10论
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(()->{
            for (int i = 1; i <= 10; i++){
               shareResource.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++){
                shareResource.print10();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++){
                shareResource.print15();
            }
        },"C").start();
    }
}

/**
 * 线程操作的资源类
 */
class ShareResource{
    // 定义线程的标识(1=》A线程，2=》B线程，3=》C线程)
    private Integer number = 1; // 默认A线程开始
    private Lock lock = new ReentrantLock();
    // 一把锁 三把钥匙，为了精确唤醒线程
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            // 1、判断
            while (number != 1){
                // 2、线程等待
                c1.await();
            }

            // 3、干活
            number = 2;
            for (int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 4、精确唤醒线程
            c2.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            // 1、判断
            while (number != 2){
                // 2、线程等待
                c2.await();
            }

            // 3、干活
            number = 3;
            for (int i = 1; i <= 10; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 4、精确唤醒线程
            c3.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            // 1、判断
            while (number != 3){
                // 2、线程等待
                c3.await();
            }

            // 3、干活
            number = 1;
            for (int i = 1; i <= 15; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 4、精确唤醒线程
            c1.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
