package com.nxtele.did.client.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PayService {

    public static boolean alipayParasVerify(Map<String,String[]> parasMap,String alipay_public_key,String sign_type){
        Map<String,String> params = new HashMap<String,String>();
        for (Iterator<String> iter = parasMap.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) parasMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, alipay_public_key, "UTF-8", sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return signVerified;
    }

}
