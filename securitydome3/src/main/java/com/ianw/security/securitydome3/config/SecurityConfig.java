package com.ianw.security.securitydome3.config;/**
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
import com.ianw.security.securitydome3.extend.CustomAuthenticationProvider;
import com.ianw.security.securitydome3.extend.CustomUserDetailService;
import com.ianw.security.securitydome3.filter.CustomCaptchaFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @program: securitydemo1
 * @description:
 * @author: tao xujie
 * @create: 2019-08-01 11:10
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final static Logger Log = LoggerFactory.getLogger(SecurityConfig.class);
   // private final static String

    /*注入数据源*/
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
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

    @Autowired
    CustomCaptchaFilter customCaptchaFilter;

    /*配置security简单的配置*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Log.debug("my first custom WebSecurityConfig Class");
        http.authorizeRequests()
                .antMatchers("/error","/captcha.img").permitAll()
                .anyRequest().access("@customAuthService.canAccess(request,authentication)")
                .and()
                .formLogin().loginPage("/mylogin").permitAll()
                .successForwardUrl("/success")
                /*.successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.getWriter().write("{\"error\":\"0\",\"message\":\"欢迎登陆\"}"); })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.setStatus(401);
                    httpServletResponse.getWriter().write("{'error':'0','message':'"+e.getMessage()+" '}");
                })*/;
       // http.sessionManagement().maximumSessions(2);
         http.csrf().disable();
        http.addFilterBefore(customCaptchaFilter, UsernamePasswordAuthenticationFilter.class);
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