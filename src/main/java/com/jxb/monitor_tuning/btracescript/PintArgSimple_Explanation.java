package com.jxb.monitor_tuning.btracescript;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * btrace脚本的编写 —— 监控简单的参数【PintArgSimple脚本的参数说明】
 * @Author: Jiangxb
 * @Date: 2020/07/18 14:59
 * 补充说明：btrace不仅仅只能拦截Controller，只要是类里面的方法，都能拦截到
 */

// 这个给注解说明：这是一个Btrace脚本
@BTrace
public class PintArgSimple_Explanation {

    /**
     * 利用@OnMethod注解标明我们需要拦截 哪个类 的 哪个方法 在 什么时候拦截
     * @param pcn   @ProbeClassName要拦截类的类名
     * @param pmn   @ProbeMethodName 要拦截方法的方法名
     * @param args  要拦截的方法参数
     */
    @OnMethod(
            clazz = "com.jxb.monitor_tuning.btrace.BtraceController",
            method = "arg1",
            location = @Location(Kind.ENTRY)  // 方法入口的时候拦截
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args){
        // 所有输出依赖这个：BTraceUtils
        BTraceUtils.printArray(args);
        BTraceUtils.println(pcn +"," + pmn);
        BTraceUtils.println();
    }

    /**
     * 拦截时机为 —— Kind.RETURN：返回
     * @param pcn
     * @param pmn
     * @param returnStr @Return 注解 标明接收返回值；不加任何注解代表参数
     */
    @OnMethod(
            clazz = "com.jxb.monitor_tuning.btrace.BtraceController",
            method = "arg1",
            location = @Location(Kind.RETURN)
    )
    public static void methodReturn(@ProbeClassName String pcn, @ProbeMethodName String pmn, @Return AnyType returnStr){
        BTraceUtils.println("Kind.RETURN");
        BTraceUtils.println(pcn +"," + pmn + ", " + returnStr);
        BTraceUtils.println();
    }
}
