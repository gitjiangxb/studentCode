package oom;

/**
 * @ClassName: StackOverflowErrorDemo
 * @Description: TODO 栈溢出错误
 * @Author: Jiangxb
 * @Date: 2020/4/5 22:31
 * @Version 1.0
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    /**
     * 方法的深度调用(递归)
     *       会导致—— 栈溢出
     */
    private static void stackOverflowError() {
        stackOverflowError();   // Exception in thread "main" java.lang.StackOverflowError
    }
}
