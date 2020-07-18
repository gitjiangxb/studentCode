package com.jxb.monitor_tuning.btracescript;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * btrace脚本的编写 —— 拦截指定行
 * @Author: Jiangxb
 * @Date: 2020/07/18 19:33
 */
@BTrace
public class PrintLine_Explanation {

    /**
     * 拦截行号
     * @param pcn
     * @param pmn
     * @param line  如果拦截到，这个就会有值
     */
    @OnMethod(
            clazz = "com.jxb.monitor_tuning.btrace.BtraceController",
            method = "exception",
            location = @Location(value = Kind.LINE,line = 67)
    )
    public static void line(@ProbeClassName String pcn, @ProbeMethodName String pmn, int line){
        BTraceUtils.println(pcn + "," + pmn + "," + line);
        BTraceUtils.println();
    }
}
