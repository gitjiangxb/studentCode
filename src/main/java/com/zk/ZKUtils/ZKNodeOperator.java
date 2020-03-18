package com.zk.ZKUtils;

import com.zk.demo.CreateCallBack;
import com.zk.watch.BaseWatch;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName: ZKNodeOperator
 * @Description: TODO ZK节点操作类
 * @Author: Jiangxb
 * @Date: 2019/9/14 10:00
 * @Version 1.0
 */
public class ZKNodeOperator {

    private static Logger log = LoggerFactory.getLogger(ZKNodeOperator.class);

    /**
     * 登录用户
     * @param scheme    代表采用的某种权限机制
     * @param user      用户名
     * @param pwd       密码
     */
    public static void addauth(String scheme,String user,String pwd){
        ZooKeeper zooKeeper = ZKConn.getInstance();
        String auth = user + ":" + pwd;
        zooKeeper.addAuthInfo(scheme,auth.getBytes());
    }

    /**
     * 同步方式创建ZK节点
     * @param path  创建的路径
     * @param data  存储的数据的byte[]
     * @param acls  控制权限策略
     *              Ids.OPEN_ACL_UNSAFE --> world:anyone:cdrwa
     *              CREATOR_ALL_ACL --> auth:user:password:cdrwa
     * @param nodeType  节点的类型
     *                  PERSISTENT：持久节点
     *                  PERSISTENT_SEQUENTIAL：持久顺序节点
     *                  EPHEMERAL：临时节点
     *                  EPHEMERAL_SEQUENTIAL：临时顺序节点
     * TODO 说明：同步或者异步创建节点，都不支持子节点的递归创建
     */
    public static void createZKNodeSync(String path, byte[] data, List<ACL> acls, CreateMode nodeType){
        String result = "";

        try {
            ZooKeeper zooKeeper = ZKConn.getInstance();
            result = zooKeeper.create(path,data,acls,nodeType);

            System.out.println("创建节点：\t" + result + "\t成功...");
        } catch (KeeperException e) {
            log.warn("创建ZK连接失败");
            e.printStackTrace();
        } catch (InterruptedException e) {
            log.warn("创建ZK节点失败");
            e.printStackTrace();
        }
    }

    /**
     * 异步方式创建ZK节点
     * @param path  创建的路径
     * @param data  存储的数据的byte[]
     * @param acls  控制权限策略
     * @param nodeType  节点的类型
     * @param createCallBack    回调方法
     * @param ctx   可做其他的操作
     * TODO 说明：同步或者异步创建节点，都不支持子节点的递归创建
     */
    public static void createZKNodeAsync(String path, byte[] data, List<ACL> acls, CreateMode nodeType, CreateCallBack createCallBack, String ctx) {
        // 异步方式，需要采用回调函数去获取创建的节点
        String result = "";

        try {
            ZooKeeper zooKeeper = ZKConn.getInstance();
            // 注：异步方式的回调函数需实现StringCallback接口
            zooKeeper.create(path,data,acls,nodeType,createCallBack,ctx);

            System.out.println("创建节点：\t" + result + "\t成功...");
            // 因为是异步方式，需要停顿下，这样回调方法才可以获取回调信息
            new Thread().sleep(2000);
        } catch (Exception e) {
            log.warn("创建ZK节点失败");
            e.printStackTrace();
        }
    }

    /**
     * 修改ZK节点的数据
     * @param path  节点路径
     * @param data  节点数据
     * @param version   数据状态(当前修改节点的版本)
     * @return
     */
    public static Stat updateNodeData(String path, byte[] data, int version){
        try {
            ZooKeeper zooKeeper = ZKConn.getInstance();
            // 注：异步方式的回调函数需实现StatCallback接口
            Stat stat = zooKeeper.setData(path, data, version);
            return stat;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除ZK节点
     * @param path  节点路径
     * @param version   数据状态(带删除节点的版本)
     */
    public static void deleteNode(String path, int version){
        try {
            ZooKeeper zooKeeper = ZKConn.getInstance();
            // 注：异步方式的回调函数需实现VoidCallback接口
            zooKeeper.delete(path,version);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到节点信息
     * @param baseWatch 为该操作绑定的watch事件
     */
    public static void getNodeData(BaseWatch baseWatch){
//        CountDownLatch countDown = new CountDownLatch(1);
        try {
            ZooKeeper zooKeeper = ZKConn.getInstance(baseWatch);
            byte[] resByte = zooKeeper.getData(baseWatch.getZkPath(), baseWatch.isWatch(), baseWatch.getStat());
            String result = new String(resByte);
            System.out.println("当前值:" + result);
            if (baseWatch.isWatch()){
                // countDown.await()    // 线程挂起
                baseWatch.getCountDown().await();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 为指定节点设置值
     * @param path  节点路径
     * @param data  值
     * @param version   当前版本
     */
    public static void setNodeData(String path,String data,int version){
        try {
            ZooKeeper zooKeeper = ZKConn.getInstance();
            Stat stat = new Stat();
            zooKeeper.getData(path,false,stat);
            int statVersion = stat.getVersion();

            if (version == statVersion){
                zooKeeper.setData(path, data.getBytes(), version);
                System.out.println("修改成功");
            } else
                System.out.println("当前输入版本不正确，应该为：" + statVersion);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取子节点数据(同步)
     * @param baseWatch 为该操作绑定的watch事件
     */
    public static void getChildrenNodeData(BaseWatch baseWatch){
        try {
            ZooKeeper zooKeeper = ZKConn.getInstance(baseWatch);
            // 同步
            List<String> childrenList = zooKeeper.getChildren(baseWatch.getZkPath(), baseWatch.isWatch());
            for (String children : childrenList){
                System.out.println("children：" + children);
            }

            if (baseWatch.isWatch()){
                // countDown.await()
                baseWatch.getCountDown().await();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取子节点数据(异步)
     * @param baseWatch 为该操作绑定的watch事件
     * @param callback  为异步操作绑定callback
     * @param isShowStas    callback是否带状态返回
     */
    public static void getChildrenNodeDataAsync(BaseWatch baseWatch, AsyncCallback callback, boolean isShowStas){
        try {
            ZooKeeper zooKeeper = ZKConn.getInstance(baseWatch);
            // 异步调用
            String ctx = "{'callback':'ChildrenCallback'}";
            if (isShowStas){
                zooKeeper.getChildren(baseWatch.getZkPath(),baseWatch.isWatch(), (AsyncCallback.Children2Callback) callback,ctx);
            }else {
                zooKeeper.getChildren(baseWatch.getZkPath(),baseWatch.isWatch(), (AsyncCallback.ChildrenCallback) callback,ctx);
            }

            if (baseWatch.isWatch()){
                // countDown.await()
                baseWatch.getCountDown().await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断某个节点是否存在
     * @param baseWatch
     */
    public static void ZKNodeExist(BaseWatch baseWatch){
        try {
            ZooKeeper zooKeeper = ZKConn.getInstance(baseWatch);
            Stat exists = zooKeeper.exists(baseWatch.getZkPath(), baseWatch);
            if (exists != null) {
                System.out.println("查询的节点版本为dataVersion：" + exists.getVersion());
            } else {
                // 此时watch事件还在，不管哪个客户端去创建这个path节点，都会触发[NodeCreated]
                System.out.println("该节点不存在...");
            }
            if (baseWatch.isWatch()){
                baseWatch.getCountDown().await();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
