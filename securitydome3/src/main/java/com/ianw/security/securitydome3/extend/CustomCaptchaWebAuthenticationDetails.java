package com.ianw.security.securitydome3.extend;/**
 * ClassName: CustomCaptchaWebAuthenticationDetails <br/>
 * Description: <br/>
 * date: 2019/8/6 15:58<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 *@program: securityproject
 *@description:
 *@author: tao xujie
 *@create: 2019-08-06 15:58
 */
public class CustomCaptchaWebAuthenticationDetails extends WebAuthenticationDetails {

    private boolean imageCodeIsRight;
    /*用户输入的验证码*/
    private String captcha;
    /*后台保存的验证码*/
    private String savedCaptcha;


    public CustomCaptchaWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.captcha = request.getParameter("captcha");

    }


}