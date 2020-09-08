package com.nxtele.did.service;


import com.nxtele.did.dto.TransferNumberDto;

/**
 * 校验接口
 * @Author: Jiangxb
 * @Date: 2020/08/17 11:18
 */
public interface CheckService {
    /**
     * 根据电话号码得到国家id【本地库里面维护的id】
     * @param callNumber
     * @return  若查到则返回具体id，否则返回0
     */
    TransferNumberDto getTransferNumberByNumber(String callNumber);

    /**
     * 校验呼叫转移时填写的号码的准确性
     * @param transferNumber
     * @return
     */
    boolean checkNumber(String transferNumber);
}
