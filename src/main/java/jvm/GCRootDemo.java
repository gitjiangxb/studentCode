package jvm;

/**
 * @ClassName: GCRootDemo
 * @Description: TODO GC Root案例
 * @Author: Jiangxb
 * @Date: 2020/3/25 9:47
 * @Version 1.0
 * Java 中可以作为GC Roots的对象
 *   1、虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中引用的对象
 *   2、方法区中的类静态属性引用的对象
 *   3、方法区中常量引用的对象
 *   4、本地方法栈中JNI（Native方法）引用的对象
 */
public class GCRootDemo {
//    private  static GCRootDemo2 t2;   // 上面第二点
//    private static final GCRootDemo3 t3 = new GCRootDemo3();  // 上面第三点

    // 方法是在栈里面的;上面第一点
    public static void m1(){
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        // main -> m1 -> t1
        m1();
    }
}
