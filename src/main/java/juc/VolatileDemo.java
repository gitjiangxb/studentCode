package juc;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: VolatileDemo
 * @Description: TODO 验证Volatile的可见性 == JMM的可见性
 * @Author: Jiangxb
 * @Date: 2020/3/2 21:37
 * @Version 1.0
 *
 * 如何验证可见性：
 *  1.1 假如int number = 0; number变量之前根本没有添加volatile关键字修饰，没有可见性
 *  1.2 假如int number = 0; number变量之前添加volatile关键字修饰，存在可见性
 *  总结：
 *      volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
 */
public class VolatileDemo {
    public static void main(String[] args) {
        // 线程要操作的资源类
        MyData myData = new MyData();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                // 暂停一会线程
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() +
                    "\t updata number value:" + myData.number);
        },"AAA").start();

        // 第2个线程就是我们的main线程
        while (myData.number == 0){
            // main线程就一直在这里等待循环，直到number值不再对于零
        }
        // 只有当main线程感知到了，number变成60后，这句话才能打印出来
        System.out.println(Thread.currentThread().getName() +
                "\t mission is over，main get number value: " + myData.number);
    }
}

/**
 * 主物理内存
 */
class MyData{
    volatile int number = 0;
    public void addTo60(){
        this.number = 60;
    }
}
