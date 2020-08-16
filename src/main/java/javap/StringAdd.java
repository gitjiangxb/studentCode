package javap;

/**
 * @ClassName: StringAdd
 * @Description: TODO 从JVM字节码分析 字符串 + 操作
 * @Author: Jiangxb
 * @Date: 2020/08/11 19:52
 * @Version 1.0
 **/
public class StringAdd {
    public static void main(String[] args) {
        f1();
        f2();
    }

    public static void f1(){
        String src = "";
        for (int i = 0 ; i < 10 ; i++ ){
            // 每一次循环都会new一个StringBuilder
            src = src + "A";
        }
        System.out.println(src);
    }

    public static void f2(){
        // 只要一个StringBuilder
        StringBuilder src = new StringBuilder();
        for (int i = 0 ; i < 10 ; i++ ){
            src.append("A");
        }
        System.out.println(src);
    }
}
