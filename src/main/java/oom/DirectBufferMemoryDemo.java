package oom;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: DirectBufferMemoryDemo
 * @Description: TODO 堆外内存溢出
 * @Author: Jiangxb
 * @Date: 2020/4/6 9:55
 * @Version 1.0
 * 导致原因：NIO
 *
 * -XX:MaxDirectMemorySize可以设置java堆外内存的峰值
 * 踪下创建DirectByteBuffer的过程：https://www.jianshu.com/p/e1503204a059
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("配置的maxDirectMemory：" +
                (sun.misc.VM.maxDirectMemory() / (double)1024 / 1024)
                + "MB");

        TimeUnit.SECONDS.sleep(3);

        // -XX:MaxDirectMemorySize=5m 我们配置为5MB，但实际使用6MB，故意使坏
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 *1024);
    }
}
