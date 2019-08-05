package com.ianw.security.securitydemo.controller;/**
 * ClassName: HelloController <br/>
 * Description: <br/>
 * date: 2019/8/2 10:49<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@program: securitydemo1
 *@description:
 *@author: tao xujie
 *@create: 2019-08-02 10:49
 */
@RestController
public class HelloController {

    @GetMapping("/user")
    public String demo2(){
        return "return user";
    }

    @GetMapping("/admin")
    public String demo3(){
        return "return admin";
    }
}