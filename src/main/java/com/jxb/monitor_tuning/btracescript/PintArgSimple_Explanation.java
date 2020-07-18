package com.jxb.monitor_tuning.btracescript;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * btrace脚本的编写 —— 监控简单的参数
 * @Author: Jiangxb
 * @Date: 2020/07/18 14:59
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
}
