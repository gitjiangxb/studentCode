package com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockDemo
 * @Description: TODO 可重入锁(也叫递归锁)
 * @Author: Jiangxb
 * @Date: 2020/3/16 11:09
 * @Version 1.0
 *  可重入锁好处：避免死锁
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();

        /**
         * 打印效果：
         * t1	 invoked sendSS()       t1线程在外层方法获取锁的时候
         * t1	 invoked sendEmail()    t1再进入内层方法会自动获取锁
         * t2	 invoked sendSS()
         * t2	 invoked sendEmail()
         */

        try {
            // 休眠一秒
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();

        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();

        /**
         * 打印效果：
         * t3	 invoked get()    t3线程在外层方法获取锁的时候
         * t3	 invoked set()    t3再进入内层方法会自动获取锁
         * t4	 invoked get()
         * t4	 invoked set()
         */

    }
}

class Phone implements Runnable{
    // 发短信
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()
                + "\t invoked sendSS()");
        /**
         *  验证synchronized是否是一个可重入锁？
         *      再一个同步方法中 调用另外一个同步方法
         */
        sendEmail();
    }

    // 发邮件
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()
                + "\t invoked sendEmail()");
    }


    /**
     * Lock的使用：
     *  lock.lock(); 与 lock.unlock();
     *      必须配对使用，不管多少把锁，都能正常运行。
     *   如果加锁个数 > 解锁个数，程序卡死
     *   如果加锁个数 < 解锁个数，程序报错：java.lang.IllegalMonitorStateException
     */
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()
                    + "\t invoked get()");
            /**
             * 验证ReentrantLock是否是一个可重入锁？
             *  在一个加锁方法中，调用另外一个加锁的方法
             */
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()
                    + "\t invoked set()");
        } finally {
            lock.unlock();
        }
    }

}