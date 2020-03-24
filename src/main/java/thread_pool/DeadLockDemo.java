package thread_pool;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: DeadLockDemo
 * @Description: TODO 死锁
 * @Author: Jiangxb
 * @Date: 2020/3/24 14:24
 * @Version 1.0
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadAAA").start();

        /**
         * linux ps -ef | grep xxx
         *
         * window下的java运行程序，也有类似ps的查看进程的命令，但是目前我们需要查看的只是java
         *      jps         == java版的ps
         *      jps -l      == Java版的 ls -l
         *
         *   需要把该进程的堆栈信息打印出来：
         *      第一步：jps -l ；在所有线程下找到对应的线程
         *      第二步：jstack 进程编号  ；打印java的异常堆栈信息
         */
    }
}

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() +
                    "\t 自己持有：" + lockA + "\t 尝试获得：" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() +
                        "\t 自己持有：" + lockB + "\t 尝试获得：" + lockA);
            }
        }
    }
}
