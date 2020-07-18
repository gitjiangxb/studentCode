package com.jxb.monitor_tuning.btracescript;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

@BTrace
public class PrintSame {

    @OnMethod(
            clazz = "com.jxb.monitor_tuning.btrace.BtraceController",
            method = "sname"
    )
    public static void same(@ProbeClassName String pcn, @ProbeMethodName String pmn,String name){
        BTraceUtils.println(pcn + "," + pmn + "," + name);
        BTraceUtils.println();
    }

    @OnMethod(
            clazz = "com.jxb.monitor_tuning.btrace.BtraceController",
            method = "sname"
    )
    public static void same(@ProbeClassName String pcn, @ProbeMethodName String pmn,int id,String name){
        BTraceUtils.println(pcn + "," + pmn + "," + name + "-" + id);
        BTraceUtils.println();
    }
}
