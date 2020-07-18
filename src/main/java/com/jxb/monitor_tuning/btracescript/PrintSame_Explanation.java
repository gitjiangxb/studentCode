package com.jxb.monitor_tuning.btracescript;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

/**
 * btrace脚本的编写 —— 拦截同名方法
 * @Author: Jiangxb
 * @Date: 2020/07/18 16:49
 */
@BTrace
public class PrintSame_Explanation {

    /**
     *  拦截同名方法 主要是根据参数的个数来区分
     * @param pcn
     * @param pmn
     * @param name
     */
    @OnMethod(
            clazz = "com.jxb.monitor_tuning.btrace.BtraceController",
            method = "sname"
    )
    public static void same(@ProbeClassName String pcn, @ProbeMethodName String pmn,String name){
        BTraceUtils.println(pcn + "," + pmn + "," + name);
        BTraceUtils.println();
    }

    /**
     * 拦截同名方法 主要是根据参数的个数来区分
     * @param pcn
     * @param pmn
     * @param id
     * @param name
     */
    @OnMethod(
            clazz = "com.jxb.monitor_tuning.btrace.BtraceController",
            method = "sname"
    )
    public static void same(@ProbeClassName String pcn, @ProbeMethodName String pmn,int id,String name){
        BTraceUtils.println(pcn + "," + pmn + "," + name + "-" + id);
        BTraceUtils.println();
    }
}
