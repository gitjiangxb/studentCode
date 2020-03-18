package com.zk.ZKUtils;

import com.zk.demo.Children2CallBack;
import com.zk.demo.ChildrenCallBack;
import com.zk.demo.CreateCallBack;
import com.zk.watch.ZKNodeWatch;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: AZJavaAPI
 * @Description: TODO 使用ZooKeeper原生Java API进行客户端开发
 * @Author: Jiangxb
 * @Date: 2019/9/14 10:32
 * @Version 1.0
 */
public class AZJavaAPI {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        String path = "/imooc";
        String data = "imooc";
        // 创建一个枚举类去操作
//        List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
        // 创建zk节点
//        ZKNodeOperator.createZKNodeSync(path,data.getBytes(),acl, CreateMode.PERSISTENT);

//        String ctx = "{'create':'success'}";
//        ZKNodeOperator.createZKNodeAsync(path,data.getBytes(),acl, CreateMode.EPHEMERAL,new CreateCallBack(),ctx);

//        Stat stat = ZKNodeOperator.updateNodeData(path, "abcd".getBytes(), 0);
//        System.out.println("修改后的版本为：" + stat.getVersion());
//        ZKNodeOperator.getNodeData(new ZKNodeWatch(path,1,true));

//        ZKNodeOperator.getChildrenNodeData(new ZKNodeWatch(path,1,true));

//        ZKNodeOperator.getChildrenNodeDataAsync(new ZKNodeWatch(path,1,true),new ChildrenCallBack(),false);

//        ZKNodeOperator.getChildrenNodeDataAsync(new ZKNodeWatch(path,1,true),new Children2CallBack(),true);

//        ZKNodeOperator.ZKNodeExist(new ZKNodeWatch(path,1,true));

//        anyoneUser();

//        digestUser();

        ipUser();
    }

    /**
     * acl 任何人都可以访问
     */
    public static void anyoneUser(){
        String  path = "/aclimooc";
        String data = "aclimooc";

        List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
        ZKNodeOperator.createZKNodeSync(path, data.getBytes(), acl, CreateMode.PERSISTENT);
    }

    /**
     * 自定义用户认证访问
     * scheme说明：
     *  world下只有一个id，即只有一个用户，也就是anyone；【 world:anyone:[permissions] 】
     *  auth：代表认证登录，需要注册用户有权限就可以；【 auth:user:password:[permissions] 】   明文
     *  digest：需要对密码加密才能访问；【 digest:username:BASE64(SHA1(password)):[permissions] 】 密文
     *  ip：当设置为ip指定的ip地址，此时限制ip进行访问；【 Ip:192.168.8.120:[permissions] 】
     *  super：代表超级管理员，拥有所有的权限
     */
    public static void digestUser() throws NoSuchAlgorithmException {
        String path = "/aclimooc/testdigest";
        String data = "testdigest";
        List<ACL> acls = new ArrayList<ACL>();
        Id imooc1 = new Id("digest",AclUtils.getDigestUserPwd("imooc1:123456"));
        Id imooc2 = new Id("digest",AclUtils.getDigestUserPwd("imooc2:123456"));
        acls.add(new ACL(ZooDefs.Perms.ALL,imooc1));    // 为imooc1用户赋予 全部权限
        acls.add(new ACL(ZooDefs.Perms.READ,imooc2));   // 为imooc2用户赋予 读权限
        acls.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.CREATE,imooc2));  // 为imooc2用户赋予 删除、创建权限
//        ZKNodeOperator.createZKNodeSync(path,data.getBytes(),acls,CreateMode.PERSISTENT);

        /**
         * 说明：此时在客户端使用：get /aclimooc/testdigest命令，会报错提示：“Authentication is not valid : /aclimooc/testdigest”
         * TODO 执行下面代码报错：org.apache.zookeeper.KeeperException$NoAuthException: KeeperErrorCode = NoAuth for /aclimooc/testdigest
         * 注：必须先使用命令 addauth digest 用户名:密码 去登录指定的用户，代码如下
         */
        ZKNodeOperator.addauth("digest","imooc1","123456");     // 返回结果为：testdigest == data
//        ZKNodeOperator.getNodeData(new ZKNodeWatch(path,1,false));    // 验证是否有权限读取数据
        path = "/aclimooc/testdigest/childtest";
        data = "childtest";
//        ZKNodeOperator.createZKNodeSync(path,data.getBytes(),acls,CreateMode.PERSISTENT);   // 验证是否有权限创建节点
        ZKNodeOperator.setNodeData(path,"newChildtest3",3);         // 验证是否有权限去修改节点信息
        ZKNodeOperator.getNodeData(new ZKNodeWatch(path,1,false));  // 读取验证后的节点信息
    }

    /**
     * acl  指定ip的方式
     */
    public static void ipUser(){
        String path = "/aclimooc/iptest6";
        String data = "iptest6";

        List<ACL> aclList = new ArrayList<ACL>();
        Id ip = new Id("ip","192.168.8.100");
        aclList.add(new ACL(ZooDefs.Perms.ALL,ip));
//        ZKNodeOperator.createZKNodeSync(path,data.getBytes(),aclList,CreateMode.PERSISTENT);

        // 验证是否有权限读取节点数据
//        ZKNodeOperator.getNodeData(new ZKNodeWatch(path,1,false));
        // 验证是否有权限修改节点数据
        ZKNodeOperator.setNodeData(path,"iptest6",1);
    }
}
