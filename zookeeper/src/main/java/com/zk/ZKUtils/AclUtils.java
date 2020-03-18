package com.zk.ZKUtils;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: AclUtils
 * @Description: TODO ACL的工具类
 * @Author: Jiangxb
 * @Date: 2019/9/24 10:14
 * @Version 1.0
 */
public class AclUtils {

    /**
     * digest：需要对密码加密才能访问
     * @param id    由 用户名:密码构成
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getDigestUserPwd(String id) throws NoSuchAlgorithmException {
        return DigestAuthenticationProvider.generateDigest(id);
    }
}
