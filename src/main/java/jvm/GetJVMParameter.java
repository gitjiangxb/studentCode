package jvm;

/**
 * @ClassName: GetJVMParameter
 * @Description: TODO 代码查看JVM的默认参数
 * @Author: Jiangxb
 * @Date: 2020/3/26 14:31
 * @Version 1.0
 */
public class GetJVMParameter {
    public static void main(String[] args) {
        // 自己电脑的核数
        int KernelNumber = Runtime.getRuntime().availableProcessors();
        // 返回Java 虚拟机 初始化堆内存（默认是自己电脑内存的1/64）
        long totalMemory = Runtime.getRuntime().totalMemory();
        // 返回Java 虚拟机 最大堆内存（默认是自己电脑内存的1/4）
        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("TOTAL_MEMORY(-Xms):" + totalMemory + "(字节)、"
                + (totalMemory / (double) 1024 /1024) + "MB");
        System.out.println("MAX_MEMORY(-Xmx):" + maxMemory + "(字节)、"
                + (maxMemory / (double) 1024 /1024) + "MB");
    }
}












