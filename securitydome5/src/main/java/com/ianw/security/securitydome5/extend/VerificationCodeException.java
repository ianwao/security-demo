package com.ianw.security.securitydome5.extend;/**
 * ClassName: VerificationCodeException <br/>
 * Description: <br/>
 * date: 2019/8/6 17:13<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import org.springframework.security.core.AuthenticationException;

/**
 *@program: securityproject
 *@description:
 *@author: tao xujie
 *@create: 2019-08-06 17:13
 */

public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException() {
        super("图形验证码校验失败");
    }

}