package com.ianw.security.securitydome5.controller;/**
 * ClassName: HelloController <br/>
 * Description: <br/>
 * date: 2019/8/2 10:49<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 *@program: securitydemo1
 *@description:
 *@author: tao xujie
 *@create: 2019-08-02 10:49
 */

@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/user")
    public String demo2(){
        logger.info("进入/user接口");
        logger.info("离开/user接口");
        return "return user";
    }
    @PostMapping("/user")
    public String postUser(@RequestParam Map<String,String> user){
        logger.info("进入/user接口");
        logger.info("user={}",user);
        logger.info("离开/user接口");
        return "添加用户成功";
    }

    @PutMapping("/user")
    public String putUser(){
        logger.info("进入/user接口");
        logger.info("离开/user接口");
        return "修改用户成功";
    }
    @DeleteMapping("/user")
    public String delUser(){
        logger.info("进入/user接口");
        logger.info("离开/user接口");
        return "删除用户成功";
    }

    @GetMapping("/admin")
    public String demo3(){
        logger.info("进入/admin接口");
        logger.info("离开/admin接口");
        return "return admin";
    }

    @GetMapping("/aa")
    public String demo4(){
        logger.info("进入/aa接口");
        logger.info("离开/aa接口");
        return "return admin";
    }

    @GetMapping("/bb")
    public String demo5(){
        logger.info("进入/bb接口");
        logger.info("离开/bb接口");
        return "return admin";
    }

    @GetMapping("/cc")
    public Object demo6(){
       Object result= restTemplate.getForObject("https://www.baidu.com/",String.class);
        return result;
    }
}