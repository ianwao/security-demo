package com.ianw.security.securitydemo2.config;/**
 * ClassName: SecurityConfig <br/>
 * Description: <br/>
 * date: 2019/8/1 11:10<br/>
 *
 * @author 72733<br/>
 * @version
 * @since JDK 1.8
 */

import com.ianw.security.securitydemo2.extend.CustomUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * @program: securitydemo1
 * @description:
 * @author: tao xujie
 * @create: 2019-08-01 11:10
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final static Logger Log = LoggerFactory.getLogger(SecurityConfig.class);

    /*注入数据源*/
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    /*配置安全登陆用户的信息*/
    //@Bean
   /* public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        //InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.setDataSource(dataSource);
        manager.createUser(User.withUsername("aa").password(passwordEncoder().encode("11")).roles("USER").build());
        manager.createUser(User.withUsername("bb").password(passwordEncoder().encode("11")).roles("ADMIN").build());
        return manager;
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(customUserDetailService)
       .passwordEncoder(passwordEncoder());
        //不要加这个super，存在配置优先级的问题
        //super.configure(auth);
        //auth.jdbcAuthentication()
                //.passwordEncoder(passwordEncoder())
                //.dataSource(dataSource);
                //.withUser("aa").password(passwordEncoder().encode("11")).roles("USER")
                //.and()
                //.withUser("bb").password(passwordEncoder().encode("11")).roles("ADMIN");

        /*auth.inMemoryAuthentication()
                .withUser("aa").password(passwordEncoder().encode("11")).roles("USER")
                .and()
                .withUser("bb").password(passwordEncoder().encode("11")).roles("ADMIN");
    */}

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

    /*配置security简单的配置*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Log.debug("my first custom WebSecurityConfig Class");
        http.authorizeRequests()
                .antMatchers("/error").permitAll()
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().access("@customUserDetailService.canAccess")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/mylogin").permitAll()
                .successForwardUrl("/success")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.getWriter().write("{\"error\":\"0\",\"message\":\"欢迎登陆\"}"); })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.setStatus(401);
                    httpServletResponse.getWriter().write("{'error':'0','message':'"+e.getMessage()+" '}");
                });
         http.csrf().disable();

        //.and().httpBasic();
    }
}