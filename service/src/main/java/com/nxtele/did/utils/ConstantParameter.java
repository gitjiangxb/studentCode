package com.nxtele.did.utils;

public class  ConstantParameter {
    /**
     * 客户DID号码状态 - 已冻结(客户列表不包含这个状态的值)
     * 触发条件：余额不足以支付月租 / 客户手动删除DID号码
     */
    public final static Integer DO_NOT_SHOW_STATUS_VALUE_FREEZING = 3;
    /**
     * 客户DID号码状态 - 已删除(客户列表不包含这个状态的值)
     * 触发条件： 客户冻结期到了后
     */
    public final static Integer DO_NOT_SHOW_STATUS_VALUE_DELETE = 4;
}
