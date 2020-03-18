package com.zk.demo;

import com.zk.ZKUtils.ZKConn;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ZKConnect
 * @Description: TODO 建立客户端与zk服务端的连接
 * @Author: Jiangxb
 * @Date: 2019/9/8 15:29
 * @Version 1.0
 */
public class ZKConnect implements Watcher{
    final static Logger log = LoggerFactory.getLogger(ZKConnect.class);

    // 单机模式
    public static final String zkServerPath = "192.168.8.120:2181";
    // 集群模式
    //	public static final String zkServerPath = "192.168.8.120:2181,192.168.8.121:2181,192.168.8.122:2181";
    public static final Integer timeout = 5000;

    public static void main(String[] args) throws Exception{
//        ZooKeeper zk = new ZooKeeper(zkServerPath,timeout,new ZKConnect());
        ZooKeeper zk = ZKConn.getInstance();
        log.warn("客户端开始连接zookeeper服务器...");
        log.warn("连接状态：{}", zk.getState());

        new Thread().sleep(2000);

        log.warn("连接状态：{}", zk.getState());

    }

    @Override
    public void process(WatchedEvent event) {
        log.warn("接受到watch通知：{}", event);
    }
}
