package com.zk.curator;

import com.zk.ZKUtils.AclUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CuratorOperator
 * @Description: TODO Curator客户端连接和关闭
 * @Author: Jiangxb
 * @Date: 2019/11/3 10:27
 * @Version 1.0
 */
public class CuratorOperator {

    public CuratorFramework client = null;
    // zk地址，集群模式下，已”,“分割
    public static final String zkServerPath = "192.168.8.120:2181";

    /**
     * 实例化zk客户端
     */
    public CuratorOperator(){
        /**
         * 说明：
         *  要创建我们的客户端，需要设置重试机制
         *      (当客户端和我们的服务器连接的时候，它会有一个初始化的
         *       连接时间，如果连接不到它会去重试)
         *  建立重试机制：五种方式
         */

        /**
         * TODO 第一种:curator连接zookeeper的策略：ExponentialBackoffRetry
         * 两种构造方法：
         *  ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries) // 默认时间：Integer.MAX_VALUE
         *  ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries, int maxSleepMs)
         *  baseSleepTimeMs：初始sleep时间（单位：毫秒）
         *  maxRetries：最大重试次数
         *  maxSleepMs：最大重试时间（单位：毫秒）
         *  ★：推荐
         */
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,5);

        /**
         * TODO 第二种:curator连接zookeeper的策略：RetryNTimes
         * 构造方法：
         *  RetryNTimes(int n, int sleepMsBetweenRetries)
         *  n：重试次数
         *  sleepMsBetweenRetries：每次重试间隔的时间
         *  ★：推荐
         */
        RetryPolicy retryPolicy = new RetryNTimes(3,5000);

        /**
         * TODO 第三种:curator连接zookeeper的策略：RetryOneTime
         * 构造方法：
         * RetryOneTime(int sleepMsBetweenRetry)
         *  sleepMsBetweenRetry：每次重试间隔时间
         *  说明：只会重试一次
         */
//        RetryPolicy retryPolicy = new RetryOneTime(3000);

        /**
         * TODO 第四种:curator连接zookeeper的策略：RetryForever
         * 永远重试，不推荐
         */
//        RetryPolicy retryPolicy = new RetryForever(1000);

        /**
         * TODO 第五种:curator连接zookeeper的策略：RetryUntilElapsed
         * 构造方法：
         *  RetryUntilElapsed(int maxElapsedTimeMs, int sleepMsBetweenRetries)
         *  maxElapsedTimeMs：最大重试时间
         *  sleepMsBetweenRetries：每次重试间隔
         *  说明：重试时间超过maxElapsedTimeMs后，就不再重试。（按时间来重试的，不是按次数）
         */
//        RetryPolicy retryPolicy = new RetryUntilElapsed(6000,3000);

        /**
         * 创建连接:
         *  connectString()     连接字符串
         *  sessionTimeoutMs()  会话时间
         *  retryPolicy()       重试机制
         *  namespace()         命名空间，代表以后的操作都在这个工作站(工作空间)中
         *  。。。。
         *
         *  重点说明：
         *      .authorization("digest","imooc1:123456".getBytes()) 权限认证(可传入List)
         */
        client = CuratorFrameworkFactory.builder() //.authorization("digest","imooc1:123456".getBytes())
                .connectString(zkServerPath)
                .sessionTimeoutMs(10000).retryPolicy(retryPolicy)
                .namespace("curator_workspace") // 相当于在ZK根目录下创建一个父节点
                .build();
        client.start();
    }

    /**
     * 关闭zk客户端连接
     */
    public void closeZkClient(){
        if (client != null ){
            this.client.close();
        }
    }

    public static void main(String[] args) throws Exception {
        // 实例化
        CuratorOperator curatorOperator = new CuratorOperator();
        boolean isZkCuratorStarted = curatorOperator.client.isStarted();
        System.out.println("当前客户端的状态：" + (isZkCuratorStarted ? "连接中" : "已关闭"));

        /**
         * TODO Curator客户端的”增删改查“
         */
//        CRUD(curatorOperator);

        /**
         * TODO usingWatcher() 的使用,只会触发一次
         */
//        usingWarcher(curatorOperator);

        /**
         * TODO NodeCache() 的使用,一次注册N次使用
         */
//        usingNodeCache(curatorOperator);

        /**
         * TODO PathChildrenCache() 的使用,一次注册N次使用,并区分监听类型
         */
//        usingPathChildrenCache(curatorOperator);


        Thread.sleep(3000000);

        curatorOperator.closeZkClient();
        boolean isZkCuratorStarted2 = curatorOperator.client.isStarted();
        System.out.println("当前客户端的状态：" + (isZkCuratorStarted2 ? "连接中" : "已关闭"));
    }

    /**
     * ACL 权限操作与认证授权
     * @param curatorOperator
     * @throws Exception
     */
    private static void usingACL(CuratorOperator curatorOperator) throws Exception{

        String path = "/acl/imooc";
        byte[] data = "testdigest".getBytes();


        List<ACL> acls = new ArrayList<>();
        // digest：需要对密码加密才能访问
        Id imooc1 = new Id("digest", AclUtils.getDigestUserPwd("imooc1:123456"));
        Id imooc2 = new Id("digest", AclUtils.getDigestUserPwd("imooc2:123456"));
        acls.add(new ACL(ZooDefs.Perms.ALL,imooc1));    // 为imooc1用户赋予 全部权限
        acls.add(new ACL(ZooDefs.Perms.READ,imooc2));   // 为imooc2用户赋予 读权限
        acls.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.CREATE,imooc2));  // 为imooc2用户赋予 删除、创建权限

        /**
         * 1、创建节点时为其设置权限
         * ZK 客户端查看：
         *  执行命令：ls /curator_workspace/acl/imooc
         *      报：Authentication is not valid : /curator_workspace/acl/imooc 【未有权限】
         *  执行命令(查看权限)：getAcl /curator_workspace/acl/imooc
         *      输出：上面三个权限[imooc1:cdrwa、imooc2:r、imooc2:cd]
         *  执行命令：getAcl /curator_workspace/acl
         *      输出：'world,'anyone /t :cdrwa        匿名的权限
         *
         *   测试二：
         *      把上面的.withACL(acls) 改为 .withACL(acls,true);
         *      同时将path变量改为page = "/acl/father/child";
         *   运行代码时报错误；然后客户端查看，在acl节点会创建father节点。
         *   这是因为目前是匿名用户登录的，而在acl目录下创建节点需要权限。
         *   因此在创建连接的时候应该为其赋予权限(修改上面代码)：
         *      client = CuratorFrameworkFactory.builder()
         *              .authorization("digest","imooc1:123456".getBytes())。。。。
         *    再次运行就会成功：
         *    客户端查看：
         *      执行命令(查看权限)：getAcl /curator_workspace/acl/father/child
         *      输出：上面三个权限[imooc1:cdrwa、imooc2:r、imooc2:cd]
         */
        curatorOperator.client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(acls)
                .forPath(path,data);

        /**
         * 2、为已存在的节点添加ACL权限
         * 区别：在进行权限设置的时候，要看当前是不是匿名状态下【'world,'anyone /t :cdrwa】；
         *  如果是匿名状态，你为一个节点设置权限是没有问题的。
         *  如果当前节点本身就有权限的时候，你要在创建连接的时候去认证：
         *      client = CuratorFrameworkFactory.builder()
         *          .authorization("digest","imooc1:123456".getBytes())。。。。
         *
         */
        curatorOperator.client.setACL().withACL(acls).forPath("/curatorNode");

        // 更新节点数据
        byte[] newData = "batman".getBytes();
        curatorOperator.client.setData()
                .withVersion(0)
                .forPath(path,newData);

        // 删除节点
        curatorOperator.client.delete()
                .guaranteed()
                .deletingChildrenIfNeeded()
                .withVersion(0)
                .forPath(path);

        // 读取节点数据
        Stat stat = new Stat();
        byte[] getData = curatorOperator.client
                .getData()
                .storingStatIn(stat)
                .forPath(path);
        System.out.println("节点：" + path + "的数据为：" + new String(getData));
        System.out.println("该节点的版本号为:" + stat.getVersion());
    }




    /**
     * Warcher的使用：只会触发一次
     * @param curatorOperator
     * @throws Exception
     */
    private static void usingWarcher(CuratorOperator curatorOperator) throws Exception{
        String nodePath = "/imooc/curator";
        /**
         * watcher事件;当使用usingWarcher的时候，监听只会触发一次，监听完毕后就销毁
         *  usingWatcher(Watcher watcher)    —— ZK原生的watcher
         *  usingWatcher(CuratorWatcher watcher) —— Curator客户端的watcher
         *
         *  说明：
         *      测试时需要在控制台测试，同时需要把主线程中的休眠时间调长
         *      测试语句1：set /curator_workspace/imooc/curator 12
         */
        curatorOperator.client.getData().usingWatcher(new MyCuratorWatcher()).forPath(nodePath);
        curatorOperator.client.getData().usingWatcher(new MyWatcher()).forPath(nodePath);
    }

    /**
     * Warcher的使用：一次注册N次使用
     * @param curatorOperator
     * @throws Exception
     */
    private static void usingNodeCache(CuratorOperator curatorOperator) throws Exception{
        String nodePath = "/imooc/curator";
        /**
         * NodeCache：监听数据节点的变更，会触发事件
         */
        final NodeCache nodeCache = new NodeCache(curatorOperator.client,nodePath);
        /**
         * start():默认 false
         * start(boolean buildInitial)
         * 说明：
         *  buildInitial：初始化的时候是否缓存node的值
         */
        nodeCache.start(true);
        if (nodeCache.getCurrentData() != null){
            byte[] data = nodeCache.getCurrentData().getData();
            System.out.println("节点初始化数据为：" + new String(data));
        } else {
            System.out.println("节点初始化数据为空。。。。");
        }
        /**
         * 为节点添加watcher
         * 监听nodePath这个节点创建了、里面数据修改了、节点被删除了
         * 示例：
         *      set /curator_workspace/imooc/curator xg
         *      delete /curator_workspace/imooc/curator
         *      create /curator_workspace/imooc/curator 123456
         */
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                // 做删除的时候需要进行校验：nodeCache.getCurrentData() != null
                String data = new String(nodeCache.getCurrentData().getData());
                System.out.println("节点路径：" + nodeCache.getCurrentData().getPath()
                + ",节点数据为：" + data);
            }
        });
    }


    /**
     * Warcher的使用：一次注册N次使用，能区分监听的类型（针对于 子节点）
     * @param curatorOperator
     * @throws Exception
     */
    private static void usingPathChildrenCache(CuratorOperator curatorOperator) throws Exception{
        String childNodePathCache = "/imooc/curator";
        /**
         * PathChildrenCache：监听数据节点的增删改，会触发事件
         * PathChildrenCache(CuratorFramework client, String path, boolean cacheData)
         * 参数说明：
         *      第一个：Curator客户端操作类
         *      第二个：需要监听的节点
         *      第三个：设置缓存节点的数据状态
         */
        final PathChildrenCache childrenCache = new PathChildrenCache(curatorOperator.client,childNodePathCache,true);
        /**
         * StartMode：初始化方式
         *  NORMAL：异步初始化
         *  BUILD_INITIAL_CACHE：同步初始化
         *  POST_INITIALIZED_EVENT：异步初始化，初始化之后触发事件
         */
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

        List<ChildData> currentDataList = childrenCache.getCurrentData();
        System.out.println("当前数据节点的子节点数据列表：");
        for (ChildData cd : currentDataList) {
            String childData = new String(cd.getData());
            System.out.println(childData);
        }

        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                if (event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)) {
                    System.out.println("子节点初始化OK。。。");
                } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)) {
                    /**
                     * 说明：这里可以对某些”节点“进行不同的处理，只需要把event.getData().getPath()得到的节点进行判断即可
                     */
                    System.out.println("添加子节点：" + event.getData().getPath());
                    System.out.println("子节点数据：" + new String(event.getData().getData()));
                } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                    System.out.println("删除子节点：" + event.getData().getPath());
                } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                    System.out.println("修改子节点：" + event.getData().getPath());
                    System.out.println("修改子节点数据：" + new String(event.getData().getData()));
                }
            }
        });
    }

    /**
     * Curator客户端 对ZK的增删改查
     * @param curatorOperator
     * @throws Exception
     */
    private static void CRUD(CuratorOperator curatorOperator) throws Exception{
        String nodePath = "/imooc/curator";
        /**
         * 创建节点（位于”curator_workspace“根目录下）
         * creatingParentsIfNeeded()   代表可以递归创建节点
         * withMode()                 节点类型(四种)
         *                  PERSISTENT：持久节点
         *                  PERSISTENT_SEQUENTIAL：持久顺序节点
         *                  EPHEMERAL：临时节点
         *                  EPHEMERAL_SEQUENTIAL：临时顺序节点
         * withACL()                    控制权限策略
         *              Ids.OPEN_ACL_UNSAFE --> world:anyone:cdrwa
         *              CREATOR_ALL_ACL --> auth:user:password:cdrwa
         */
        byte[] data = "curator test".getBytes();
        curatorOperator.client
                .create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(nodePath,data);

        /**
         * 更新节点数据
         */
        byte[] newData = "curator updata".getBytes();
//        curatorOperator.client.setData()
//                .withVersion(0)
//                .forPath(nodePath,newData);

        /**
         * 删除节点
         *  guaranteed()    如果删除失败，那么在后端还是会继续删除，直到成功
         *  deletingChildrenIfNeeded()  如果有子节点，就删除
         *  =====> 这两个方法可以不加
         */
//        curatorOperator.client.delete()
//                .guaranteed()
//                .deletingChildrenIfNeeded()
//                .withVersion(0)
//                .forPath(nodePath);

        /**
         * TODO 查询节点信息
         * 1、读取节点数据
         */
        Stat stat = new Stat();
//        byte[] nodeData = curatorOperator.client.getData()
//                .storingStatIn(stat)    // 可写可不写，获取数据的同时拉取状态
//                .forPath(nodePath);
//        System.out.println("节点：" + nodePath + " 的数据为：" + new String(data));
//        System.out.println("该节点的版本号为：" + stat.getVersion());

        /**
         * 2、查询子节点
         *  为了验证，利用命令行的形式创建子节点
         *  create /curator_workspace/imooc/curator/childrenNode children
         */
//        List<String> childNodes = curatorOperator.client.getChildren()
//                .forPath(nodePath);
//        System.out.println("开始打印子节点：");
//        for (String str : childNodes){
//            System.out.println("子节点：" + str);
//        }

        /**
         * 判断节点是否存在，如果不存在则为空
         */
//        Stat statExist = curatorOperator.client.checkExists()
//                .forPath("/imooc/curator");
//        if (statExist == null){
//            System.out.println("当前节点：" + nodePath + " 不存在");
//        } else {
//            System.out.println("当前节点：" + nodePath + "存在且版本为：" + statExist.getVersion());
//        }

    }
}
