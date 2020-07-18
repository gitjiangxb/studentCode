package com.jxb.monitor_tuning.btrace;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
