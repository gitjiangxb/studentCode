package com.zk.curator;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

/**
 * @ClassName: MyCuratorWatcher
 * @Description: TODO 具体watch，如同com.zk.watch.ZKNodeWatch写法
 * @Author: Jiangxb
 * @Date: 2020/2/1 17:54
 * @Version 1.0
 */
public class MyCuratorWatcher implements CuratorWatcher {
    @Override
    public void process(WatchedEvent event) throws Exception {
        System.out.println("触发watcher，节点路径为：" + event.getPath());
    }
}
