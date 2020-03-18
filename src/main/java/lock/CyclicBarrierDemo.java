package lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName: CyclicBarrierDemo
 * @Description: TODO 做加法
 * @Author: Jiangxb
 * @Date: 2020/3/17 22:50
 * @Version 1.0
 * 说明：
 *  只有等所有的同学到齐后才能开始开班会
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,
                ()->{
                    System.out.println("*****班会开始*****");
                });
        for (int i = 1; i <= 7; i++){
            final int tempInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +
                        "\t 第" + tempInt + "位同学已到！");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }

}
