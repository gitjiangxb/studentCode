package com.zk.ZKUtils;

import com.zk.watch.BaseWatch;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ZKConn
 * @Description: TODO ZK单例连接类
 * @Author: Jiangxb
 * @Date: 2019/9/10 10:36
 * @Version 1.0
 */
public class ZKConn{
    private static Logger log = LoggerFactory.getLogger(ZKConn.class);

    // 单机模式
    private static final String zkServerPath = "192.168.8.120:2181";
    // 集群模式
//    public static final String zkServerPath = "192.168.8.120:2181,192.168.8.121:2181,192.168.8.122:2181";
    private static final Integer timeOut = 10000;


    private static ZooKeeper zooKeeper;

    private ZKConn(){}

    public static ZooKeeper getInstance() {
        if (zooKeeper == null)
            synchronized (ZKConn.class) {
                if (zooKeeper == null) {
                    try {
                        zooKeeper = new ZooKeeper(zkServerPath, timeOut, new BaseWatch());
                        // TODO 可以采用匿名内部类的方式/也可以通过声明实例的时候传入一个自定义的Watcher类
//                        zooKeeper = new ZooKeeper(zkServerPath, timeOut, new Watcher() {
//                            @Override
//                            public void process(WatchedEvent event) {
//                                log.warn("接受到watch通知：{}", event);
//                            }
//                        });

                        log.warn("客户端开始连接zookeeper服务器...");
                        log.warn("连接状态：{}", zooKeeper.getState());

                        new Thread().sleep(2000);   // 测试用

                        log.warn("连接状态：{}", zooKeeper.getState());
                    } catch (Exception e) {
                        log.warn("创建zk连接失败");
                        e.printStackTrace();
                    }
                }
            }
        return zooKeeper;
    }

    /**
     * 使用自定义watch创建zkService
     * @param watcher
     * @return
     */
    public static ZooKeeper getInstance(Watcher watcher) {
        if (zooKeeper == null)
            synchronized (ZKConn.class) {
                if (zooKeeper == null) {
                    try {
                        zooKeeper = new ZooKeeper(zkServerPath, timeOut, watcher);
                        log.warn("客户端开始连接zookeeper服务器...");
                        log.warn("连接状态：{}", zooKeeper.getState());

                        new Thread().sleep(2000);   // 测试用

                        log.warn("连接状态：{}", zooKeeper.getState());
                    } catch (Exception e) {
                        log.warn("创建zk连接失败");
                        e.printStackTrace();
                    }
                }
            }
        return zooKeeper;
    }

}
