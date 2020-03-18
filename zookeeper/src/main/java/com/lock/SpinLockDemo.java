package com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: SpinLockDemo
 * @Description: TODO   手写自旋锁
 * @Author: Jiangxb
 * @Date: 2020/3/16 15:18
 * @Version 1.0
 */
public class SpinLockDemo {

    // 原子应用线程(引用的默认值为：null)
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void myLock(){
        Thread thread = Thread.currentThread(); // 当前进入的线程
        System.out.println(thread.getName() + "\t come in");
        /**
         * compareAndSet(V expect, V update) 底层调用UnSafe的方法
         *  expect：期望值
         *  update：更新值
         * 如果期望值为null，就更新为 当前线程
         */
        while (!atomicReference.compareAndSet(null,thread)){
            // 自旋
        }
    }

    // 解锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName() + "\t invoked myUnLock()");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLock = new SpinLockDemo();

        new Thread(()->{
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnLock();
        },"AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnLock();
        },"BB").start();
    }
}
