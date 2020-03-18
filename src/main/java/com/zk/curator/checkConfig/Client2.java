package com.zk.curator.checkConfig;

import com.zk.curator.checkConfig.util.JsonUtils;
import com.zk.curator.checkConfig.util.RedisConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: Client2
 * @Description: TODO   客户端2
 * @Author: Jiangxb
 * @Date: 2020/2/9 13:23
 * @Version 1.0
 * 说明：当ZK的节点发生改变的时候，所有监听它的客户端都
 *  一起去修改，而不是一个一个手动去修改。
 */
public class Client2 {
    public CuratorFramework client = null;
    public static final String zkServerPath = "192.168.8.120:2181";

    public Client2(){
        RetryPolicy retryPolicy = new RetryNTimes(3,5000);
        client = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .namespace("curator_workspace")
                .build();
        client.start();
    }

    public void closeZKClient(){
        if (client != null){
            this.client.close();
        }
    }

    //	public final static String CONFIG_NODE = "/imooc/curator/redis-config";
    public final static String CONFIG_NODE_PATH = "/imooc/curator";   // 监听的父目录
    public final static String SUB_PATH = "/redis-config";          // 在监听父目录下创建的节点
    // 需要让主线程一直挂起的状态
    public static CountDownLatch countDown = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        Client2 cto = new Client2();
        System.out.println("Client1启动成功。。。");

        final PathChildrenCache childrenCache = new PathChildrenCache(cto.client,CONFIG_NODE_PATH,true);
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

        // 添加监听事件
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                // 监听节点变化(节点更新)
                if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)){

                    String configNodePath = event.getData().getPath();

                    if (configNodePath.equals(CONFIG_NODE_PATH + SUB_PATH)){
                        System.out.println("监听到配置发生变化，节点路径为:" + configNodePath);

                        // 读取节点数据
                        String jsonConfig = new String(event.getData().getData());
                        System.out.println("节点" + CONFIG_NODE_PATH + "的数据为: " + jsonConfig);

                        // 从json转换配置
                        RedisConfig redisConfig = null;
                        if (StringUtils.isNotBlank(jsonConfig)) {
                            redisConfig = JsonUtils.jsonToPojo(jsonConfig, RedisConfig.class);
                        }

                        // 配置不为空则进行相应操作
                        if (redisConfig != null) {
                            String type = redisConfig.getType();
                            String url = redisConfig.getUrl();
                            String remark = redisConfig.getRemark();
                            // 判断事件
                            if (type.equals("add")) {
                                System.out.println("监听到新增的配置，准备下载...");
                                // ... 连接ftp服务器，根据url找到相应的配置
                                Thread.sleep(500);
                                System.out.println("开始下载新的配置文件，下载路径为<" + url + ">");
                                // ... 下载配置到你指定的目录
                                Thread.sleep(1000);
                                System.out.println("下载成功，已经添加到项目中");
                                // ... 拷贝文件到项目目录
                            } else if (type.equals("update")) {
                                System.out.println("监听到更新的配置，准备下载...");
                                // ... 连接ftp服务器，根据url找到相应的配置
                                Thread.sleep(500);
                                System.out.println("开始下载配置文件，下载路径为<" + url + ">");
                                // ... 下载配置到你指定的目录
                                Thread.sleep(1000);
                                System.out.println("下载成功...");
                                System.out.println("删除项目中原配置文件...");
                                Thread.sleep(100);
                                // ... 删除原文件
                                System.out.println("拷贝配置文件到项目目录...");
                                // ... 拷贝文件到项目目录
                            } else if (type.equals("delete")) {
                                System.out.println("监听到需要删除配置");
                                System.out.println("删除项目中原配置文件...");
                            }

                            // TODO 视情况统一重启服务(因为这里目前是修改了redis的配置信息，也该重启服务)
                        }
                    }

                }
            }
        });
        countDown.await();

        cto.closeZKClient();
    }
}
