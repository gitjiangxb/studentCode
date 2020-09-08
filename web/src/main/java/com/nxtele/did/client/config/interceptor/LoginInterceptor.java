package com.nxtele.did.client.config.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return true;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With,usertoken,admintoken");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE");

        // 获取用户token
        String usertoken = request.getHeader("usertoken");  // 客户端token
        // 获取管理员token
        String admintoken = request.getHeader("admintoken"); // 管理员token
        String url=request.getRequestURI();
        if (url.startsWith("/pay") || url.startsWith("/vxpay") || url.startsWith("/didClient/publicprice")){
            return true;
//            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        System.out.println(url);
        String customerId = null;
        String adminId = null;
        if( !StringUtils.isEmpty(usertoken) ) { // 用户token 检验 用户
            // 携带一个token就允许登录   因为客户端只能携带 usertoken   管理端只能携带 admintoken
            customerId = "";
        }else if ( !StringUtils.isEmpty(admintoken)){ // admintoken 不为空
            adminId = "";
        }else {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("500");
            out.close();
            response.flushBuffer();
            return false;
        }

        if (StringUtils.isEmpty(customerId) && StringUtils.isEmpty(adminId)){
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("500");
            out.close();
            response.flushBuffer();
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
