package thread_pool;

import java.util.concurrent.*;

/**
 * @ClassName: MyThreadPoolDemo
 * @Description: TODO 线程池的简单案例
 * @Author: Jiangxb
 * @Date: 2020/3/23 14:36
 * @Version 1.0
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        int KernelNumber = Runtime.getRuntime().availableProcessors();
        System.out.println("查看自己电脑的核数：" + KernelNumber);
        /**
         * Array -> 辅助工具类Arrays
         * Collection -> 辅助工具类Collections
         * Executor -> 辅助工具类Executors
         */
        // 使用JDK内置的Executors取创建线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);    // 一池5个处理线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();    // 一池1个处理线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();    // 一池N个处理线程（至于到底多少个，由它自己控制）


        /**
         * 生产上常用！！！
         * 使用ThreadPoolExecutor创建自定义线程池
         * 这个线程池最大能处理的线程任务数(触发拒绝策略)：maximumPoolSize + BlockingQueueSize
         */
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,          // 常驻核心线程数
                KernelNumber*2,      // 最大线程数（应该根据CPU的核数来确定大小）
                2L,         // 空闲线程的存活时间
                TimeUnit.SECONDS,       // 空闲线程的存活时间 的单位
                new LinkedBlockingDeque<>(3),       // 任务队列
                Executors.defaultThreadFactory(),           // 生成线程池中工作线程的线程工厂
                new ThreadPoolExecutor.AbortPolicy()        // 拒绝策略
                );
        ThreadPoolDemo(threadPool);
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
