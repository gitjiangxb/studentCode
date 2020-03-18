package com.zk.curator;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @ClassName: MyWatcher
 * @Description: TODO 具体watch，如同com.zk.watch.ZKNodeWatch写法
 * @Author: Jiangxb
 * @Date: 2020/2/1 17:56
 * @Version 1.0
 */
public class MyWatcher implements Watcher {
    @Override
    public void process(WatchedEvent event) {
        System.out.println("触发watcher，节点路径为：" + event.getPath());
    }
}
