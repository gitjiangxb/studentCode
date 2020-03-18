package com.zk.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: BaseWatch
 * @Description: TODO 默认的Watch事件
 * @Author: Jiangxb
 * @Date: 2019/9/23 11:02
 * @Version 1.0
 */
public class BaseWatch implements Watcher {
    private static Logger log = LoggerFactory.getLogger(BaseWatch.class);

    private boolean isWatch;    // 是否注册一个watch事件

    /**
     * TODO 利用CountDownLatch来实现zkNode的Watcher监控
     */
    private CountDownLatch countDown;

    private String zkPath;  // 节点路径
    private Stat stat;      // 状态，跟客户端利用【get path】返回的信息一样

    public BaseWatch(){}

    public BaseWatch(String zkPath, int countDownCount, boolean isWatch){
        this.zkPath = zkPath;
        this.stat = new Stat();
        this.countDown = new CountDownLatch(countDownCount);
        this.isWatch = isWatch;
    }

    public String getZkPath() {
        return zkPath;
    }

    public void setZkPath(String zkPath) {
        this.zkPath = zkPath;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public CountDownLatch getCountDown() {
        return countDown;
    }

    public void setCountDown(CountDownLatch countDown) {
        this.countDown = countDown;
    }

    public boolean isWatch() {
        return isWatch;
    }

    public void setWatch(boolean watch) {
        isWatch = watch;
    }

    @Override
    public void process(WatchedEvent event) {
        log.warn("接受到watch通知：{}", event);
    }
}
