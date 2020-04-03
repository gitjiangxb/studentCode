package reference;

/**
 * @ClassName: StrongReferenceDemo
 * @Description: TODO 强引用
 * @Author: Jiangxb
 * @Date: 2020/4/3 16:00
 * @Version 1.0
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        // 这样定义的默认就是强引用
        Object obj1 = new Object();

        // obj2 引用赋值
        Object obj2 = obj1;

        // 置空
        obj1 = null;

        // 启动GC
        System.gc();

        // obj2没有被垃圾回收
        System.out.println(obj2);
    }
}
