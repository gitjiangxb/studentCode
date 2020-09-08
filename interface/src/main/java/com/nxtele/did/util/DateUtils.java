package com.nxtele.did.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
    *                        用于 解决 mybatis映射实体类多 .0的问题   Date ==> String
    * @author gc
    * @date   2020/8/20 10:37
    * @param
    * @return
    */
    public static String format(Date date){
        String format = formater.format(date);
        return format;
    }


    /**
    *                        用于解决  格式 yyyy-MM-dd
    * @author gc
    * @date   2020/8/20 10:43
    * @param
    * @return
    */
    public static String formatDay(Date date){
        return dateFormat.format(date);
    }





}
