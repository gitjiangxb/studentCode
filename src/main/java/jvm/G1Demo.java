package jvm;

import java.util.Random;

/**
 * @ClassName: G1Demo
 * @Description: TODO G1垃圾收集器
 * @Author: Jiangxb
 * @Date: 2020/7/6 19:02
 * @Version 1.0
 *  参数配置：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseG1GC
 */
public class G1Demo {
    public static void main(String[] args) {
        String str = "jiangxb";
        while (true){
            str += str + new Random().nextInt(77777777) + new Random().nextInt(88888888);
            str.intern();
        }
    }
}
