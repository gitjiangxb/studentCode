package oom;

/**
 * @ClassName: UnableCreateNewThreadDemo
 * @Description: TODO 无法创建新的本地线程 错误
 * @Author: Jiangxb
 * @Date: 2020/4/6 10:59
 * @Version 1.0
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 1 ; ; i++){
            System.out.println("************ i = " + i);
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }
}
