package com.ianw.security.securitydome5.controller;/**
 * ClassName: WebController <br/>
 * Description: <br/>
 * date: 2019/8/1 10:27<br/>
 *
 * @author 72733<br   />
 * @version
 * @since JDK 1.8
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: securitydemo1
 * @description: 前端请求处理
 * @author: tao xujie
 * @create: 2019-08-01 10:27
 */

@Controller
public class WebController {

    private final static Logger logger = LoggerFactory.getLogger(WebController.class);

    @PostMapping("/success")
    @ResponseBody
    public String demo1() {
        logger.info("进入/success接口");
        logger.info("离开/uccess接口");
        return "this is my first secueity demo";
    }

    @GetMapping("/mylogin")
    public String demo2() {
        logger.info("进入/mylogin接口");
        logger.info("离开/mylogin接口");
        return "mylogin";
    }

    @GetMapping("/")
    public String demo3() {
        logger.info("进入/接口");
        logger.info("离开/接口");
        return "index";
    }
}