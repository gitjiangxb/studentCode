package reference;

import java.lang.ref.SoftReference;

/**
 * @ClassName: SoftReferenceDemo
 * @Description: TODO 软引用
 * @Author: Jiangxb
 * @Date: 2020/4/3 22:06
 * @Version 1.0
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
//        softRef_Memory_Enough();

        softRef_Memory_NotEnough();
    }

    /**
     * 软引用 内存够用的情况——保留
     */
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        // 软引用
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        // 置空后再启动垃圾回收
        o1 = null;
        System.gc();

        System.out.println(o1);     // 打印：null
        System.out.println(softReference.get());    // 打印：变量内存地址
    }

    /**
     * 软引用 内存不够用的情况——回收
     *
     * JVM配置：故意产生大对象并配置小的内存，让它内存不够用导致OOM，看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        // 软引用
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
         } catch (Throwable e){
            e.printStackTrace();
        } finally {
            System.out.println(o1);     // 打印：null
            System.out.println(softReference.get());    // 打印：null
        }
    }
}
