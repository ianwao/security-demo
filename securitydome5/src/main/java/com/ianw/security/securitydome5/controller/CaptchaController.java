package com.ianw.security.securitydome5.controller;/**
 * ClassName: CaptchaController <br/>
 * Description: <br/>
 * date: 2019/8/5 20:57<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *@program: securityproject
 *@description:  设置验证码的接口
 *@author: tao xujie
 *@create: 2019-08-05 20:57
 */
@Controller
public class CaptchaController {

    private final static Logger logger = LoggerFactory.getLogger(CaptchaController.class);

    @Autowired
    Producer captchaProducer;

    private static final String CAPTCHA_SESSION_KEY="captcha";

    @RequestMapping(value = "/captcha.img",method = RequestMethod.GET)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置内容类型
        response.setContentType("image/jpeg");

        // 创建验证码文本
        String capText = captchaProducer.createText();

        // 将验证码文本设置到 session
        request.getSession().setAttribute(CAPTCHA_SESSION_KEY, capText);

        // 创建验证码图片
        BufferedImage bi = captchaProducer.createImage(capText);

        // 获取响应输出流
        ServletOutputStream out = response.getOutputStream();

        // 将图片验证码数据写到响应输出流
        ImageIO.write(bi, "jpg", out);

        // 推送并关闭响应输出流
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}