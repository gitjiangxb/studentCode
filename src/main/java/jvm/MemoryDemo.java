package jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: MemoryDemo
 * @Description: TODO 堆/非堆内存溢出案例演示
 * @Author: Jiangxb
 * @Date: 2020/7/9 19:02
 * @Version 1.0
 */
public class MemoryDemo {

    private static List<User> userList = new ArrayList<User>();
    private static List<Class<?>> classList = new ArrayList<Class<?>>();

    public static void main(String[] args) {
        heap();
//        noHeap();
    }


    /**
     * 堆内存溢出案例测试
     * 参数配置：-Xms10m -Xmx10m -XX:+PrintGCDetails
     *  内存溢出自动导出(只需要配置参数)： -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
     * 错误信息：Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    public static void heap(){
        int i = 0;
        while (true){
            userList.add(new User(i++, UUID.randomUUID().toString()));
        }
    }

    /**
     * 非堆内存溢出案例
     * 说明：Metaspace里面主要保存：一些class、method。。。
     *
     * 参数配置：-XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m -XX:+PrintGCDetails
     *
     * 错误信息：Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
     */
    public static void noHeap(){
        int i = 0;
        while (true){
            // 动态生成足够多的class
            classList.addAll(Metaspace.createClasses());
        }
    }

}

class User{
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
