package jvm;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: HelloGC
 * @Description: TODO JVM -XX参数示例
 * @Author: Jiangxb
 * @Date: 2020/3/25 10:44
 * @Version 1.0
 * 如何查看一个正在运行中的java程序它的某个jvm参数是否开启？具体值是多少？
 *  答：先利用"jps -l"得到线程号，再利用"jinfo -flag 参数名 进程号" 去查看具体参数的值
 *
 * 总结：
 *  第一种查看参数盘点家底方式
 *      jps -l 得到对应的线程编号
 *      jinfo -flag 具体参数 java进程编号
 *      jinfo -flags        java进程编号
 *  第二种查看参数盘点家底方式
 *      java -XX:+PrintFlagsInitial -version
 *      java -XX:+PrintFlagsInitial
 *  第三种查看参数盘点家底方式(能查看当前默认的垃圾回收器是哪个？)
 *      -XX:+PrintCommandLineFlags -version
 *      -XX:+PrintCommandLineFlags
 *
 *   输出GC日志的参数：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:C:/Jiangxb/code/MySelf/studentDoc/gc.log -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("******HelloGC********");
        // new一个超过15M的变量去验证垃圾回收
        byte[] bytes = new byte[15 * 1024 * 1024];
//        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
