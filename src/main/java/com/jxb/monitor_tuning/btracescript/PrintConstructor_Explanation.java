package com.jxb.monitor_tuning.btracescript;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

/**
 * btrace脚本的编写 —— 拦截构造函数
 * @Author: Jiangxb
 * @Date: 2020/07/18 16:49
 */
@BTrace
public class PrintConstructor_Explanation {

    @OnMethod(
            clazz = "com.jxb.monitor_tuning.entity.User",   // 需要拦截的类
            method = "<init>"           // 拦截类的 构造函数 必须这么写
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
        BTraceUtils.println(pcn+","+pmn);
        BTraceUtils.printArray(args);
        BTraceUtils.println();
    }
}
