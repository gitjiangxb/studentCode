package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CPUDemo
 * @Description: TODO 死循环导致CPU飙高
 * @Author: Jiangxb
 * @Date: 2020/7/10 18:39
 * @Version 1.0
 */
public class CPUDemo {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        List<Long> loop = loop();
//        String deadlock = deadlock();
    }

    /**
     * 死循环问题
     * @return
     * 说明：
     *  测试方式，应该使用springboot来创建这个案例，然后写个Controller。
     *  然后利用men打包提交到服务器上面，然后多有几个请求去访问。主要查看
     *  cpu的负载达到了多少，负载过大，新的请求是无法访问的。
     */
//    @RequestMapping("/loop")
    public static List<Long> loop(){
        String data = "{\"data\":[{\"partnerid\":]";
        return getPartneridsFromJson(data);
    }

    /**
     * 死锁问题
     * @return
     */
//    @RequestMapping("/deadlock")
    public static String deadlock(){
        new Thread(()->{
            synchronized(lock1) {
                try {Thread.sleep(1000);}catch(Exception e) {}
                synchronized(lock2) {
                    System.out.println("Thread1 over");
                }
            }
        }) .start();
        new Thread(()->{
            synchronized(lock2) {
                try {Thread.sleep(1000);}catch(Exception e) {}
                synchronized(lock1) {
                    System.out.println("Thread2 over");
                }
            }
        }) .start();
        return "deadlock";
    }

    public static List<Long> getPartneridsFromJson(String data){
        //{\"data\":[{\"partnerid\":982,\"count\":\"10000\",\"cityid\":\"11\"},{\"partnerid\":983,\"count\":\"10000\",\"cityid\":\"11\"},{\"partnerid\":984,\"count\":\"10000\",\"cityid\":\"11\"}]}
        //上面是正常的数据
        List<Long> list = new ArrayList<Long>(2);
        if(data == null || data.length() <= 0){
            return list;
        }
        int datapos = data.indexOf("data");
        if(datapos < 0){
            return list;
        }
        int leftBracket = data.indexOf("[",datapos);
        int rightBracket= data.indexOf("]",datapos);
        if(leftBracket < 0 || rightBracket < 0){
            return list;
        }
        String partners = data.substring(leftBracket+1,rightBracket);
        if(partners == null || partners.length() <= 0){
            return list;
        }
        while(partners!=null && partners.length() > 0){
            int idpos = partners.indexOf("partnerid");
            if(idpos < 0){
                break;
            }
            int colonpos = partners.indexOf(":",idpos);
            int commapos = partners.indexOf(",",idpos);
            if(colonpos < 0 || commapos < 0){
                //partners = partners.substring(idpos+"partnerid".length());//1
                continue;
            }
            String pid = partners.substring(colonpos+1,commapos);
            if(pid == null || pid.length() <= 0){
                //partners = partners.substring(idpos+"partnerid".length());//2
                continue;
            }
            try{
                list.add(Long.parseLong(pid));
            }catch(Exception e){
                //do nothing
            }
            partners = partners.substring(commapos);
        }
        return list;
    }
}
