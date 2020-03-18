package com.container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @ClassName: ContainerNotSafeDemo
 * @Description: TODO 集合类不安全的问题
 * @Author: Jiangxb
 * @Date: 2020/3/4 13:28
 * @Version 1.0
 * ArrayList 是线程不安全的，为了保证并发性和效率，它的add方法没有加synchronized
 * 但是 Vector 这个集合实现类的add方法是加了synchronized的，能保证数据一致性，但是并发性下降
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
//        listNotSafe();
//        setNotSafe();
        mapNotSafe();

        Lock nonfairSync = new ReentrantLock();
        Lock fairSync = new ReentrantLock(true);
    }

    public static void mapNotSafe(){
//        Map<String,String> map = new HashMap<>();
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <= 20; i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }).start();
        }

        /**
         * 1、故障现象
         *   java.util.ConcurrentModificationException
         * 2、解决方案
         *   2.1 new ConcurrentHashMap<>();
         * 引伸而出的问题？
         *  HashMap 与 ConcurrentHashMap有什么不同之处？
         *   JDK1.8中的实现
         *      ConcurrentHashMap取消了segment分段锁，而采用CAS和synchronized
         *     来保证并发安全。数据结构跟HashMap1.8的结构一样，数组+链表+红黑二叉树。
         */
    }

    public static void setNotSafe(){
        Set<String> setThread = new HashSet<>();
//        Set<String> setThread = Collections.synchronizedSet(new HashSet<>());
//        Set<String> setThread = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++){
            new Thread(()->{
                setThread.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(setThread);
            },String.valueOf(i)).start();
        }

        /**
         * 1、故障现象
         *   java.util.ConcurrentModificationException
         * 2、解决方案
         *   2.1 Collections.synchronizedSet(new HashSet<>());
         *   2.2 new CopyOnWriteArraySet<>(); // CopyOnWrite容器即 写时复制的容器。
         * 引伸而出面试题：
         *  1、HashSet 底层是什么？
         *      答：HashMap
         *  2、那为什么new HashSet<>().add("A");的add()只有一个参数？
         *    而HashMap的add()有两个参数
         *    答：源码如下
         *      public boolean add(E e) {
         *       return map.put(e, PRESENT)==null;
         *      }
         *      那是因为HashSet的一个值是HashMap的key，HashMap的value是一个常量PRESENT：
         *      private static final Object PRESENT = new Object();
         */
    }


    public static void listNotSafe(){
        // 单线程情况下 不会出错。
        List<String> list = Arrays.asList("a","b","c","d");
//        list.forEach(System.out::println);

        /**
         * 多线程情况下：
         *  当在高并发多线程情况下，ArrayList常见的异常
         *      java.util.ConcurrentModificationException —— 并发修改的异常
         */
        List<String> listThread = new ArrayList();

        for (int i = 1; i <= 30; i++){
            new Thread(()->{
                listThread.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(listThread);
            },String.valueOf(i)).start();
        }

        /**
         * 1、故障现象
         *   java.util.ConcurrentModificationException
         * 2、导致原因
         *   并发争抢修改导致，类似我们的花名册签名情况。
         *      一个人正在写入，另一个同学过来抢夺，导致数据不一致异常。并发修改异常
         * 3、解决方案
         *   3.1 new Vector<>();
         *   3.2 Collections.synchronizedList(new ArrayList<>());
         *   3.3 new CopyOnWriteArrayList(); // CopyOnWrite容器即 写时复制的容器。
         * 4、优化建议（同样的错误不犯第二次）
         *
         */
    }
}
