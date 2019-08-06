package com.ianw.security.securitydome3.extend;/**
 * ClassName: CustomCaptchaWebAuthenticationDetails <br/>
 * Description: <br/>
 * date: 2019/8/6 15:58<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import com.ianw.security.securitydome3.config.SecurityConfig;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

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
        //获取用户填写的验证码
        this.captcha = request.getParameter(SecurityConfig.CAPTCHA_SESSION_KEY);
        //获取后台存储的验证码
        this.savedCaptcha = (String) request.getSession().getAttribute(SecurityConfig.CAPTCHA_SESSION_KEY);
        if (!StringUtils.isEmpty(this.savedCaptcha)) {
            // 清除验证码，不管是失败，还是成功，所有客户端都应在登录失败时刷新验证码
            request.getSession().removeAttribute(SecurityConfig.CAPTCHA_SESSION_KEY); // 当验证码正确时设置状态
            if (!StringUtils.isEmpty(captcha) && captcha.equals(savedCaptcha)) {
                this.imageCodeIsRight = true;
            }
        }

    }
    public boolean getImageCodeIsRight() {
        return this.imageCodeIsRight;
    }


}