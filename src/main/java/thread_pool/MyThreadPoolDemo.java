package thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: MyThreadPoolDemo
 * @Description: TODO 线程池的简单案例
 * @Author: Jiangxb
 * @Date: 2020/3/23 14:36
 * @Version 1.0
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println("查看自己电脑的核数：" + Runtime.getRuntime().availableProcessors());
        /**
         * Array -> 辅助工具类Arrays
         * Collection -> 辅助工具类Collections
         * Executor -> 辅助工具类Executors
         */
        ThreadPoolDemo(Executors.newFixedThreadPool(5));    // 一池5个处理线程
//        ThreadPoolDemo(Executors.newSingleThreadExecutor());    // 一池1个处理线程
//        ThreadPoolDemo(Executors.newCachedThreadPool());    // 一池N个处理线程（至于到底多少个，由它自己控制）

    }

    /**
     * 模拟10个用户去银行办理业务，每个用户就是一个来自外部的请求线程
     * @param threadPool
     */
    public static void ThreadPoolDemo(ExecutorService threadPool){
        try {
            for (int i = 1; i <= 10; i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() +"\t 办理业务");
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
