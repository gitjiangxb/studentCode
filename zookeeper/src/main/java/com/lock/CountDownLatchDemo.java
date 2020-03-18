package com.lock;

import java.util.concurrent.CountDownLatch;
/**
 * @ClassName: CountDownLatchDemo
 * @Description: TODO 做减法
 * @Author: Jiangxb
 * @Date: 2020/3/17 10:35
 * @Version 1.0
 */
public class CountDownLatchDemo {
    private static final Integer studentNum = 6;
    public static void main(String[] args) throws Exception {
//        closeDoor();
        /**
         * 现在有个疑问，其他学生离开教室时，并没有带上自己的名称。
         */
        killCountry();
    }

    /**
     * 秦始皇统一六国，结合枚举的使用
     * @throws Exception
     */
    private static void killCountry() throws Exception{
        CountDownLatch downLatch = new CountDownLatch(studentNum);
        for (int i = 1; i <= studentNum; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+
                        "\t 灭亡");
                downLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }

        downLatch.await();
        System.out.println(Thread.currentThread().getName() +
                "\t 秦始皇统一六国");
    }

    /**
     * 模拟班长等所有学生都出去后关门
     * @throws Exception
     */
    private static void closeDoor() throws Exception{
        CountDownLatch downLatch = new CountDownLatch(studentNum);
        for (int i = 1; i <= studentNum; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+
                        "\t 离开教室");
                downLatch.countDown();
            },String.valueOf(i)).start();
        }

        /**
         * 默认后台存在两个线程：1是main线程，2是后台GC线程
         * 注意：为什么不能使用这个，而是需要使用CountDownLatch？
         *      ？？？？？？？？？
         */
//        while (Thread.activeCount() > 2){
//            Thread.yield(); // 礼让线程
//        }
        downLatch.await();
        System.out.println(Thread.currentThread().getName() +
                "\t 班长最后锁教室");
    }
}

/**
 * 枚举的使用：简而言之 相当于一张数据库的表
 */
enum CountryEnum{
    ONE(1,"齐国"),TWO(2,"楚国"),THREE(3,"燕国"),
    FPUR(4,"赵国"),FIVE(5,"魏国"),SIX(6,"韩国");

    private Integer retCode;
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }
    public Integer getRetCode() {
        return retCode;
    }
    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }
    public String getRetMessage() {
        return retMessage;
    }
    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum element : myArray){
            if (element.retCode == index){
                return element;
            }
        }
        return null;
    }
}
