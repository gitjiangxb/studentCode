package com.jxb.monitor_tuning.btracescript;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

@BTrace
public class PrintOnThrow {
    // store current exception in a thread local
    // variable (@TLS annotation). Note that we can't
    // store it in a global variable!
    @TLS
    static Throwable currentException;

    // introduce probe into every constructor of java.lang.Throwable
    // class and store "this" in the thread local variable.
    @OnMethod(
            clazz="java.lang.Throwable",
            method="<init>"
    )
    public static void onthrow(@Self Throwable self) {//new Throwable()
        currentException = self;
    }

    @OnMethod(
            clazz="java.lang.Throwable",
            method="<init>"
    )
    public static void onthrow1(@Self Throwable self, String s) {//new Throwable(String msg)
        currentException = self;
    }

    @OnMethod(
            clazz="java.lang.Throwable",
            method="<init>"
    )
    public static void onthrow1(@Self Throwable self, String s, Throwable cause) {//new Throwable(String msg, Throwable cause)
        currentException = self;
    }

    @OnMethod(
            clazz="java.lang.Throwable",
            method="<init>"
    )
    public static void onthrow2(@Self Throwable self, Throwable cause) {//new Throwable(Throwable cause)
        currentException = self;
    }

    // when any constructor of java.lang.Throwable returns
    // print the currentException's stack trace.
    @OnMethod(
            clazz="java.lang.Throwable",
            method="<init>",
            location=@Location(Kind.RETURN)
    )
    public static void onthrowreturn() {
        if (currentException != null) {
            BTraceUtils.Threads.jstack(currentException);
            BTraceUtils.println("=====================");
            currentException = null;
        }
    }
}
