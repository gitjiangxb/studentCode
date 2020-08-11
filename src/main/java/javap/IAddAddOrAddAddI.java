package javap;

/**
 * @ClassName: IAddAddOrAddAddI
 * @Description: TODO 从JVM字节码分析 i++ 与 ++i的不同
 * @Author: Jiangxb
 * @Date: 2020/08/11 19:52
 * @Version 1.0
 *  i++：先用后加    ++i：先加后用
 **/
public class IAddAddOrAddAddI {
    public static void main(String[] args) {
        iAddAdd();
        addAddI();
    }

    public static void iAddAdd(){
        int i = 0;
        int j = i++;
        System.out.println(j);
    }

    public static void addAddI(){
        int i = 0;
        int j = ++i;
        System.out.println(j);
    }
}
