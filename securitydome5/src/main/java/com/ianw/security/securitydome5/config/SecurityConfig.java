package com.ianw.security.securitydome5.config;/**
 * ClassName: SecurityConfig <br/>
 * Description: <br/>
 * date: 2019/8/1 11:10<br/>
 *
 * @author 72733<br/>
 * @version
 * @since JDK 1.8
 */


import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.ianw.security.securitydome5.extend.CustomAuthenticationProvider;
import com.ianw.security.securitydome5.extend.CustomCaptchaWebAuthenticationDetailsSource;
import com.ianw.security.securitydome5.extend.CustomUserDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Properties;

/**
 * @program: securitydemo1
 * @description:
 * @author: tao xujie
 * @create: 2019-08-01 11:10
 */

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final static Logger Log = LoggerFactory.getLogger(SecurityConfig.class);
    public static final String CAPTCHA_SESSION_KEY = "captcha";

    @Autowired
    private CustomUserDetailService customUserDetailService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService(),passwordEncoder()));
    }

    /*配置*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/css/**","/js/**","/images/**");
    }

    /*配置密码解析器*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> customCaptchaWebAuthenticationDetailsSource() {
        return new CustomCaptchaWebAuthenticationDetailsSource();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService(), passwordEncoder());
    }

    /*配置security简单的配置*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Log.debug("my first custom WebSecurityConfig Class");
        http.authorizeRequests()
                .antMatchers("/error","/captcha.img").permitAll()
                .anyRequest().access("@customAuthService.canAccess(request,authentication)")
                .and()
                .formLogin().authenticationDetailsSource(new CustomCaptchaWebAuthenticationDetailsSource())
                .loginPage("/mylogin").permitAll();
       http.sessionManagement().maximumSessions(1);
       http.logout().logoutSuccessUrl("/");
         http.csrf().disable();
    }


    @Bean
    public Producer captcha() { // 配置图形验证码的基本参数
        Properties properties = new Properties();
        // 图片长度
        properties.setProperty("kaptcha.image.width", "150");
        // 图片宽度
        properties.setProperty("kaptcha.image.height", "50");
        // 字符集
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
        // 字符长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);

        // 使用默认的图形验证码实现，当然也可以自定义实现
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}