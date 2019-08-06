package com.ianw.security.securitydome3.extend;/**
 * ClassName: CustomCaptchaWebAuthenticationDetailsSource <br/>
 * Description: <br/>
 * date: 2019/8/6 16:45<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 *@program: securityproject
 *@description:
 *@author: tao xujie
 *@create: 2019-08-06 16:45
 */
public class CustomCaptchaWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest,WebAuthenticationDetails> {


    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new CustomCaptchaWebAuthenticationDetails(request);
    }
}