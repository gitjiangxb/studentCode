package com.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SemaphoreDemo
 * @Description: TODO 信号量
 * @Author: Jiangxb
 * @Date: 2020/3/18 11:12
 * @Version 1.0
 *  示例：三个车位六辆车抢，停三秒后离开，其他车辆继续进入
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        /**
         * Semaphore(int permits, boolean fair)
         *   permits：几个信号量/几个许可证
         *   fair：不公平锁
         * Semaphore(int permits)   默认就是不公平锁
         */
        // 三个车位
        Semaphore semaphore = new Semaphore(3);

        // 模拟六辆车
        for (int i = 1; i <= 6; i++){
            new Thread(()->{
                try {
                    semaphore.acquire();    // 获得
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                try {
                    // 停车三秒后离开停车场
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t停车三秒后离开");
                semaphore.release();    // 释放
            },String.valueOf(i)).start();
        }
    }
}
