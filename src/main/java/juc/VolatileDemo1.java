package juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: VolatileDemo1
 * @Description: TODO 验证volatile不保证原子性
 * @Author: Jiangxb
 * @Date: 2020/3/3 9:26
 * @Version 1.0
 * 2 验证volatile不保证原子性
 *  2.1 原子性指的是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以
 *    被加塞或者被分割。需要整体完整--要么同时成功，要么同时失败。
 *  2.2 volatile为什么不保证原子性？
 *      数值小于20000，出现了丢失写值的情况(线程自己的工作内存往主内存写)；
 *      看下面的JVM字节码截图
 *  2.3 如何解决原子性？
 *      第一种：加synchronized关键字（）
 *      第二种：使用JUC下带原子的类型：AtomicInteger
 *          引入：为什么加了Atomic的可以保证原子性？ 【CAS】
 */
public class VolatileDemo1 {
    public static void main(String[] args) {
        MyData1 myData = new MyData1();

        /**
         * 20个线程同时调用addPlusPlus()方法1000次，去验证最后结果是否正确？
         *  number = 20 * 1000 = 20000
         */
        for (int i = 1; i <= 20; i++){
            new Thread(()->{
                for (int j = 1; j <= 1000; j++){
                    myData.addPlusPlus();   // 不保证原子性
                    myData.addAtomic();     // 保证原子性
                }
            },String.valueOf(i)).start();
        }
        // 需要等待上面20个线程都全部计算完成后，再用main线程去获取最终结果

        /**
         * 这个就是多线程控制线程执行时间的最好的方法。
         *  等所有线程都执行完成后再调用，为什么是 >2 ?
         *      因为默认后台存在两个线程：1是main线程，2是后台GC线程
         */
        while (Thread.activeCount() > 2){
            Thread.yield(); // 礼让线程
        }
        System.out.println(Thread.currentThread().getName() +
                "\t int type,finally number value：" + myData.number);
        System.out.println(Thread.currentThread().getName() +
                "\t AtomicInteger type,finally number value：" + myData.atomicInteger);
    }
}

class MyData1{  // MyData1.java ===>  MyData1.class ===> JVM字节码
    volatile int number = 0;

    // 请注意，此时number变量前添加了volatile关键字
    public  void addPlusPlus(){
        /**
         * number ++; 在多线程下是非线程安全的，如何不加synchronized解决？
         * 说明：
         *  该方法加了synchronized关键字，某一刻时间内只能有一个线程能访问。
         *  加了这个关键字：最终结果就是：number = 20000。
         *  但是在这里不建议使用“synchronized”，它太重了，还有其他方法？
         */
        this.number ++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}