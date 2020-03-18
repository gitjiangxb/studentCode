package com.zk.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: ZKNodeOperator
 * @Description: TODO ZK的操作类
 * @Author: Jiangxb
 * @Date: 2019/9/8 21:35
 * @Version 1.0
 */
public class ZKNodeOperator implements Watcher{

    private ZooKeeper zooKeeper = null;

    public final static String zkServerPath = "192.168.8.120:2181";
    public final static Integer timeOut = 5000;

    public ZKNodeOperator(){}

    public ZKNodeOperator(String connectString){
        try {
            zooKeeper = new ZooKeeper(connectString,timeOut,new ZKNodeOperator());
        } catch (IOException e){
            e.printStackTrace();
            // 当zk不为空时，关闭连接
            if (zooKeeper != null){
                try {
                    zooKeeper.close();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建zk节点
     * @param path 创建的路径
     * @param data 数据
     * @param acls 权限
     */
    public void createZKNode(String path, byte[] data, List<ACL> acls){
        String result = "";

        try {
            /**
             * 同步或者异步创建节点，都不支持子节点的递归创建，异步有一个callback函数
             * 参数：
             * path：创建的路径
             * data：存储的数据的byte[]
             * acl：控制权限策略
             * 			Ids.OPEN_ACL_UNSAFE --> world:anyone:cdrwa
             * 			CREATOR_ALL_ACL --> auth:user:password:cdrwa
             * createMode：节点类型, 是一个枚举
             * 			PERSISTENT：持久节点
             * 			PERSISTENT_SEQUENTIAL：持久顺序节点
             * 			EPHEMERAL：临时节点
             * 			EPHEMERAL_SEQUENTIAL：临时顺序节点
             */
            result = zooKeeper.create(path, data, acls, CreateMode.PERSISTENT);

            // TODO 异步方式
//            String ctx = "{'create':'success'}";
//            zooKeeper.create(path, data, acls, CreateMode.PERSISTENT, new CreateCallBack(), ctx);

            System.out.println("创建节点：\t" + result + "\t成功...");
            new Thread().sleep(2000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        // 创建zk连接服务
        ZKNodeOperator zkServer = new ZKNodeOperator(zkServerPath);
        // 创建zk节点，并赋予默认权限【'world,'anyone: cdrwa】
        zkServer.createZKNode("/imooc","imooc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
    }
}
