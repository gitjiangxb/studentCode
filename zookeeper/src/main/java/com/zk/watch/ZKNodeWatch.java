package com.zk.watch;

import com.zk.ZKUtils.ZKConn;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: ZKNodeWatch
 * @Description: TODO   监控zk节点的watch事件
 * @Author: Jiangxb
 * @Date: 2019/9/23 11:03
 * @Version 1.0
 * 通常自定义实现
 */
public class ZKNodeWatch extends BaseWatch {

    public ZKNodeWatch(String zkPath, int countDownCount, boolean isWatch) {
        super(zkPath, countDownCount,isWatch);
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            if(event.getType() == Event.EventType.NodeDataChanged){
                System.out.println("[NodeDataChanged]");
                ZooKeeper zooKeeper = ZKConn.getInstance();

                byte[] resByte = zooKeeper.getData(this.getZkPath(), false, this.getStat());
                String result = new String(resByte);
                System.out.println("更改后的值:" + result);
                System.out.println("版本号变化dversion：" + this.getStat().getVersion());
                this.getCountDown().countDown();

            } else if(event.getType() == Event.EventType.NodeCreated) {
                System.out.println("[NodeCreated]");
                this.getCountDown().countDown();

            } else if(event.getType() == Event.EventType.NodeChildrenChanged) {
                // 当path下面的子节点发生了变化(子节点增加/删除)，就会进入这里
                System.out.println("[NodeChildrenChanged]");
                ZooKeeper zooKeeper = ZKConn.getInstance();
                List<String> childrenList = zooKeeper.getChildren(event.getPath(), false);
                for (String children : childrenList) {
                    System.out.println("children:" + children);
                }
                this.getCountDown().countDown();

            } else if(event.getType() == Event.EventType.NodeDeleted) {
                System.out.println("[NodeDeleted]");
                this.getCountDown().countDown();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
