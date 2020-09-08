package com.nxtele.did.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DaysUtils {

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
    *                       获取今天到
    * @author gc
    * @date   2020/7/14 10:29
    * @param
    * @return
    */
    public static BigDecimal getDays() {
        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数
        int last = calendar.getActualMaximum(calendar.DAY_OF_MONTH); // 获取本月最大天数
        return new BigDecimal(last - (day-1)).divide(new BigDecimal(last),3, BigDecimal.ROUND_HALF_UP);
    }

    public static String nowFormat(){
        Date date = new Date();
        String today = DaysUtils.format.format(date);
        return today;
    }




}
