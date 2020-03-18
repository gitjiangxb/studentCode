package com.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: ReadWriteLockDemo
 * @Description: TODO 读写锁
 * @Author: Jiangxb
 * @Date: 2020/3/16 22:14
 * @Version 1.0
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源类，就不应该再有其他线程可以对该资源进行读或者写
 * 小总结：
 *      读-读能共存
 *      读-写不能共存
 *      写-写不能共存
 *   写操作：
 *      必须保证原子性+独占；整个过程必须是一个完整的统一体，中间不许被分割，被打断
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCatch myCatch = new MyCatch();
        MyCatchLock myCatchLock = new MyCatchLock();
        for (int i = 1; i <= 5; i++){
            final int tempInt = i;
            new Thread(()->{
                myCatch.put(tempInt+"",tempInt+"");
//                myCatchLock.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++){
            final int tempInt = i;
            new Thread(()->{
                myCatch.get(tempInt+"");
//                myCatchLock.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}

// 自己写一个缓存(普通的，没有加锁，不能保证写独立)
class MyCatch{
    // 共享变量 应该加“volatile”已保证可见性和禁止指令重排
    private volatile Map<String,Object> map = new HashMap<>();
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
        map.put(key,value);
        // 线程休眠一会
        try {
            TimeUnit.MICROSECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 写入完成");
    }

    public void get(String key){
        System.out.println(Thread.currentThread().getName() + "\t 正在读取");
        Object result = map.get(key);
        // 线程休眠一会
        try {
            TimeUnit.MICROSECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 读取成功：" + result);
    }

    public void clear(){
        map.clear();
    }
}

// 自己写一个缓存(加锁的，保证写独立)
class MyCatchLock{
    // 共享变量 应该加“volatile”已保证可见性和禁止指令重排
    private volatile Map<String,Object> map = new HashMap<>();
    // 读写锁
    private ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        rrwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            map.put(key,value);
            // 线程休眠一会
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            rrwLock.writeLock().unlock();
        }
    }

    public void get(String key){
        rrwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取");
            Object result = map.get(key);
            // 线程休眠一会
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t 读取成功：" + result);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            rrwLock.readLock().unlock();
        }
    }

    public void clear(){
        map.clear();
    }
}

