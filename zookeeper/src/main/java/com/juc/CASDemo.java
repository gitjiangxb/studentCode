package com.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CASDemo
 * @Description: TODO CAS
 * @Author: Jiangxb
 * @Date: 2020/3/3 16:01
 * @Version 1.0
 *
 * 1、CAS是什么？    ===> compareAndSet
 *  比较并交换
 * 	CAS包含了三个操作数
 * 		内存值：V
 * 		预估值：A
 * 		更新值：B
 * 	当且仅当 V == A时，V=B；否则，将什么操作也不做。
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        /**
         * compareAndSet(int expect, int update)
         * expect：期望值/预估值
         * update：更新值
         */
        System.out.println(atomicInteger.compareAndSet(5,2019) +
                "\t current data :" + atomicInteger);

        System.out.println(atomicInteger.compareAndSet(5,1024) +
                "\t current data :" + atomicInteger);

        atomicInteger.getAndIncrement();

    }
}
