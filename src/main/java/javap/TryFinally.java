package javap;

/**
 * @ClassName: TryFinally
 * @Description: TODO 从JVM字节码分析 Try-Finally
 * @Author: Jiangxb
 * @Date: 2020/08/16 16:06
 * @Version 1.0
 **/
public class TryFinally {
    public static void main(String[] args) {
        System.out.println(f1());
    }

    public static String f1(){
        String str = "hello";
        try {
            return str;
        } finally {
            str = "imooc";
        }
    }
}
