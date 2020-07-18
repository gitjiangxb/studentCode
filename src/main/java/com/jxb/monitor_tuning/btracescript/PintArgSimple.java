package com.jxb.monitor_tuning.btracescript;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;


@BTrace
public class PintArgSimple {
    @OnMethod(
            clazz = "com.jxb.monitor_tuning.btrace.BtraceController",
            method = "arg1",
            location = @Location(Kind.ENTRY)
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args){
        BTraceUtils.printArray(args);
        BTraceUtils.println(pcn +"," + pmn);
        BTraceUtils.println();
    }

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
