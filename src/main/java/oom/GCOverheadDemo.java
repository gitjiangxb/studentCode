package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GCOverheadDemo
 * @Description: TODO 超出GC开销限制
 * @Author: Jiangxb
 * @Date: 2020/4/5 23:03
 * @Version 1.0
 * JVM参数配置演示：
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e){
            System.out.println("*************** i :" + i);
            // java.lang.OutOfMemoryError: GC overhead limit exceeded
            e.printStackTrace();
            throw e;
        }
    }
    /**
     * 主要看副GC回收的情况：
     * [名称：GC前内存占用 -> GC后内存占用 (该区内存总大小)]
     * [Full GC (Ergonomics) [PSYoungGen: 2047K->2047K(2560K)] [ParOldGen: 7085K->7085K(7168K)] 9133K->9133K(9728K),
     * [Metaspace: 3447K->3447K(1056768K)], 0.0567448 secs] [Times: user=0.09 sys=0.00, real=0.06 secs]
     */
}
