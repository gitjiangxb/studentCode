package com.jxb.monitor_tuning.btrace;

import com.jxb.monitor_tuning.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;

/**
 * btrace的使用
 * @Author: Jiangxb
 * @Date: 2020/07/18 14:37
 * 说明：让这个接口运行起来，然后用btrace脚本来监控这个接口
 */
@RestController
@RequestMapping("/btrace")
public class BtraceController {

    /**
     * 测试1：在程序不停止的状态下，利用Btrace获取该接口的参数
     * @param name
     * @return
     */
    @PostMapping("/arg1")
    public String arg1(@RequestParam("name") String name){
        return "hello ," + name;
    }


    /**
     * 测试2：利用btrace拦截构造函数
     * @param user
     * @return
     */
    @PostMapping("/constructor")
    @ResponseBody
    public User constructor(@RequestBody User user){
        return user;
    }

    /**
     * 测试3：btrace拦截同名方法
     * @param name
     * @return
     */
    @PostMapping("/sname1")
    public String sname(@RequestParam("name") String name){
        return "hello ," + name;
    }

    /**
     * 测试3：btrace拦截同名方法
     * @param name
     * @return
     */
    @PostMapping("/sname2")
    public String sname(@RequestParam("id") int id,@RequestParam("name") String name){
        return "hello , " + id + ":" + name;
    }
}
