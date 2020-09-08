package com.nxtele.did.utils;

import java.math.BigDecimal;

public class MathDurationTypeUtil {

    public static BigDecimal DurationTypeToTime(Integer type){

        switch (type){
            case 0:
                return new BigDecimal(1);   // 返回一个月
            case 1:
                return new BigDecimal(6);  // 返回 六个月
            case 2:
                return new BigDecimal(12); // 返回 12个月
        }
        return null;
    }




}
