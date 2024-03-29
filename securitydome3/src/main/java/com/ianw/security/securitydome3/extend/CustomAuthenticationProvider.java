package com.ianw.security.securitydome3.extend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * ClassName: CustomAuthenticationProvider <br/>
 * Description: <br/>
 * date: 2019/8/6 15:25<br/>
 *
 * @author 72733<br />
 * @since JDK 1.8
 */


//public class CustomAuthenticationProvider implements AuthenticationProvider {
//public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
      this.setUserDetailsService(userDetailsService);
      this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException {

        //实现图形验证码的校验逻辑
        CustomCaptchaWebAuthenticationDetails details =
                (CustomCaptchaWebAuthenticationDetails) usernamePasswordAuthenticationToken.getDetails();

        // 发现验证码不正确，就立刻抛出相应异常信息
        if (!details.getImageCodeIsRight()) {
            throw new VerificationCodeException();
        }

        // 调用父类方法完成密码验证
        super.additionalAuthenticationChecks(userDetails,
                usernamePasswordAuthenticationToken);
    }

}
