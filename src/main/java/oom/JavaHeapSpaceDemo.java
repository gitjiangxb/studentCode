package oom;

import java.util.Random;

/**
 * @ClassName: JavaHeapSpaceDemo
 * @Description: TODO 堆内存不够用错误
 * @Author: Jiangxb
 * @Date: 2020/4/5 22:43
 * @Version 1.0
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {

        /**
         * 为了验证堆内存不够用，修改参数：
         *  -Xms5m -Xmx5m
         *  导致原因：对象太多了
         */

//        byte[] bytes = new byte[30 * 1024 *1024];

        String name = "jxb";
        while (true){
            // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            name += name + new Random().nextInt(1111111) + new Random().nextInt(22222);
            name.intern();
        }

    }
}
