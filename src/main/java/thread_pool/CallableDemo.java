package thread_pool;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CallableDemo
 * @Description: TODO
 * @Author: Jiangxb
 * @Date: 2020/3/23 10:48
 * @Version 1.0
 * 在多线程中，获取线程的方式：
 *  1、继承Thread类,重写run方法
 *  2、实现Runnable接口,实现run方法
 *  3、实现Callable接口，实现call方法
 *  4、
 * Runnable接口 与 Callable接口 的区别：
 *  1、Runnable接口没有返回值；Callable接口有返回值
 *  2、Runnable接口不会抛异常，Callable接口会抛异常
 *  3、接口实现的方法不一样；
 *      Runnable接口,实现run方法
 *      Callable接口，实现call方法
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        // FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread3());

        new Thread(futureTask,"AA").start();
        /**
         * 说明：多个线程抢一个futureTask的时候，计算结果只会计算一次。【同样的futureTask结果复用】
         * 如果需要计算多次则：
         *  FutureTask<Integer> futureTask02 = new FutureTask<Integer>(new MyThread3());
         *  new Thread(futureTask02,"BB").start();
         */
        new Thread(futureTask,"BB").start();

//        Integer result02 = futureTask.get();  // 测试阻塞
        /**
         * 如果将futureTask.get(); 放在前面执行，下面的代码会被阻塞，待到Callable的call()方法执行完成后才能继续执行下面的代码
         */
        System.out.println(Thread.currentThread().getName() + "\t ******是否会阻塞？********");
        Integer result01 = 100;
        /**
         * FutureTask.get() 建议放在最后
         *      因为目前程序有两个线程：一个main主线程，一个是 AA -futureTask
         * get()方法要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致
         *  阻塞，直到计算完成
         */

//        while (!futureTask.isDone()){
//            // futureTask线程执行完毕后，退出循环；继续执行其他的
//        }
        Integer result02 = futureTask.get();
        System.out.println("*******result:" + (result01 + result02));
    }
}

/**
 * 多种使用线程的方式
 */
class MyThread extends Thread{ // 几乎不用了
    @Override
    public void run() {
        super.run();
    }
}

class MyThread2 implements Runnable{
    @Override
    public void run() {

    }
}

/**
 * 新的一种方式：
 *  Callable怎么用? 【适配器模式】
 *      利用第三个接口来适配：FutureTask
 *         class FutureTask<V> implements RunnableFuture<V>
 *         interface RunnableFuture<V> extends Runnable, Future<V>
 *         public FutureTask(Callable<V> callable) {  FutureTask类的构造方法 }
 *  返回值怎么取？
 *      FutureTask.get() 方法获取返回值
 */
class MyThread3 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t ******come in Callable********");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}