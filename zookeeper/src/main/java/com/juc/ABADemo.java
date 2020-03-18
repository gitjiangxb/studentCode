package com.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName: ABADemo
 * @Description: TODO ABA问题的解决  AtomicStampedReference
 * @Author: Jiangxb
 * @Date: 2020/3/3 21:53
 * @Version 1.0
 */
public class ABADemo {
    // 普通的原子引用
    static AtomicReference<Integer> atomicReference =
            new AtomicReference<>(100);
    static AtomicStampedReference<Integer> aStampedReference =
            new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        System.out.println("==============以下是ABA问题的产生=================");
        new Thread(() ->{
            // ABA操作
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(() ->{
            try {
                // t2线程暂停1秒钟，保证上面的t1线程完成了一次ABA操作
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100,201) +
                    "\t" + atomicReference.get());
            // 输出：true	201
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("==============以下是ABA问题的解决=================");
        new Thread(() ->{
            // 拿到版本号
            int stamp = aStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName() +
                        "\t 第1次版本号：" + stamp);
                try {
                    // t3线程暂停1秒钟
                    TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**
             * compareAndSet(V   expectedReference,V   newReference,
             *              int expectedStamp,int newStamp)
             *  参数说明:
             *      expectedReference：期望值
             *      newReference：更新值
             *      expectedStamp：期望版本号
             *      newStamp：更新版本号
             */
            aStampedReference.compareAndSet(100,101,
                    aStampedReference.getStamp(), aStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() +
                    "\t 第2次版本号：" + aStampedReference.getStamp());
            aStampedReference.compareAndSet(101,100,
                    aStampedReference.getStamp(), aStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() +
                    "\t 第3次版本号：" + aStampedReference.getStamp());
        },"t3").start();

        new Thread(() ->{
            // 拿到版本号
            int stamp = aStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() +
                    "\t 第1次版本号：" + stamp);
            try {
                // t4线程暂停3秒钟，保证上面的t3线程完成了一次ABA操作
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = aStampedReference.compareAndSet(100, 201, stamp, stamp + 1);
            int endStamp = aStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() +
                    "\t 修改成功与否：" + result + ",最终版本号：" + endStamp);
            System.out.println("当前实际值：" + aStampedReference.getReference());
        },"t4").start();

    }
}
