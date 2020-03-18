package com.zk.demo;


import org.apache.zookeeper.AsyncCallback.StringCallback;

/**
 * @ClassName: CreateCallBack
 * @Description: TODO
 * @Author: Jiangxb
 * @Date: 2019/9/8 21:46
 * @Version 1.0
 */
public class CreateCallBack implements StringCallback {

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("创建节点: " + path);
        System.out.println((String)ctx);
    }
}
