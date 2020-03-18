package com.zk.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ZKConnectSessionWatcher
 * @Description: TODO zookeeper恢复之前的会话连接
 * @Author: Jiangxb
 * @Date: 2019/9/8 21:08
 * @Version 1.0
 * TODO 端点代码测试，打开Linux命令行，采用：echo dump | nc 192.168.8.120 2181
 */
public class ZKConnectSessionWatcher implements Watcher{

    final static Logger log = LoggerFactory.getLogger(ZKConnectSessionWatcher.class);

    public static final String zkServerPath = "192.168.8.120:2181";
    public static final Integer timeOut = 5000;

    public static void main(String[] args) throws Exception{
        ZooKeeper zk = new ZooKeeper(zkServerPath,timeOut,new ZKConnectSessionWatcher());
        // TODO 将sessionId 和 密码 保存起来，方便重连
        long sessionId = zk.getSessionId();
        byte[] sessionPassword = zk.getSessionPasswd();

        String ssid = "0x" + Long.toHexString(sessionId);
        System.out.println("sessionId：" + ssid);

        log.warn("客户端开始连接zookeeper服务器。。。");
        log.warn("连接状态：{}",zk.getState());
        new Thread().sleep(1000);
        log.warn("连接状态：{}",zk.getState());

        // TODO 开始会话重连
        new Thread().sleep(2000);
        log.warn("开始会话重连。。。");

        ZooKeeper zkSession = new ZooKeeper(zkServerPath,timeOut,
                new ZKConnectSessionWatcher(),
                sessionId,sessionPassword);
        log.warn("重新连接状态zkSession：{}",zkSession.getState());
        new Thread().sleep(1000);
        log.warn("重新连接状态zkSession：{}",zkSession.getState());
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.warn("接受到watch通知：{}", watchedEvent);
    }
}
