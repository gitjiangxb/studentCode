package jvm;

import java.util.Random;

/**
 * @ClassName: GCDemo
 * @Description: TODO 垃圾收集器配置代码示例
 * @Author: Jiangxb
 * @Date: 2020/5/5 14:35
 * @Version 1.0
 * 1、
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC     （DefNew + Tenured）
 *
 * 2、
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC     （ParNew + Tenured）
 *
 * 3、
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC     （PSYoungGen + ParOldGen）
 *
 * 4、
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC     （PSYoungGen + ParOldGen）
 *  (不加就是默认的 UseParallelGC)
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags     （PSYoungGen + ParOldGen）
 *
 * 5、
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC    （par new generation + concurrent mark-sweep generation）
 *
 * 6、
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC    后面单独讲解G1
 *
 * 7、(理论知道即可，实际中(JDK8)已经被优化掉了，没有了)
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 *
 * 下面是故意繁琐配置的，主要是为了学习，一般生产不这么配置
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC -XX:+UseParallelOldGC     （PSYoungGen + ParOldGen）
 *
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC -XX:+UseConcMarkSweepGC     （par new generation + concurrent mark-sweep generation）
 */
public class GCDemo {
    public static void main(String[] args) {
        System.out.println("*********GCDemo hello");
        try {
            String str = "jiangxb";
            while (true){
                str += str + new Random().nextInt(7777777)+new Random().nextInt(888888);
                str.intern();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
