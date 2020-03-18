package com.juc;

/**
 * @ClassName: SingletonDemo
 * @Description: TODO 单例模式
 * @Author: Jiangxb
 * @Date: 2020/3/3 15:23
 * @Version 1.0
 */
public class SingletonDemo {

    // 使用volatile关键字 防止指令重排
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() +
                "\t 我是构造方法SingletonDemo()");
    }

    /**
     * DCL(Double Check Lock双端检锁机制)
     * 注意-存在潜在的隐患：编译器的指令重排
     * @return
     */
    public static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        /**
         * 并发多线程后，情况发生了变化;会打印多次
         * 解决办法：
         *  在getInstance()方法上加一个synchronized关键字；这个虽然可以解决，但是不推荐！！
         *    在高并发情况下，推荐【单例模式DCL（Double Check Lock 双端检锁机制）】
         */
        for (int i = 1; i <= 10; i++){
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
