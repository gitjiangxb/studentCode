package javap;

/**
 * @ClassName: StringConstantVariable
 * @Description: TODO 从JVM字节码分析 String常量变量
 * @Author: Jiangxb
 * @Date: 2020/08/16 16:56
 * @Version 1.0
 **/
public class StringConstantVariable {
    public static void main(String[] args) {
        f1();
        f2();
    }

    public static void f1(){
        final String x = "hello";
        final String y = x + "world";
        String z = x + y;
        System.out.println(z);
    }

    public static void f2(){
        final String x = "hello";
        String y = x + "world";
        String z = x + y;
        System.out.println(z);
    }
}
