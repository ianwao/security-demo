package com.ianw.security.securitydome3.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomCaptchaFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(CustomCaptchaFilter.class);

    private AuthenticationFailureHandler authenticationFailureHandler =
            (httpServletRequest, httpServletResponse, e) -> {
                //httpServletResponse.sendRedirect("/mylogin");
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                httpServletResponse.setStatus(401);
                httpServletResponse.getWriter().write("{'error':'0','message':'验证码输入错误'}");
            };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*日志打印*/
        logger.info("CustomFilter,servletRequest={},servletResponse={}", request.getParameter("captcha"), response);

        /*对其他方法进行放行*/
        if (!"/mylogin".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
        } else if ("/mylogin".equals(request.getRequestURI()) && request.getMethod().equals("GET")) {
            filterChain.doFilter(request, response);
        } else {
            String captchaback = (String) request.getSession().getAttribute("captcha");
            String captcha = request.getParameter("captcha");

            if (!StringUtils.isEmpty(captcha) && !StringUtils.isEmpty(captchaback) && captcha.equals(captchaback)) {

                filterChain.doFilter(request, response);
            }else {

                authenticationFailureHandler.onAuthenticationFailure(request, response, null);
            }
            /*移除验证码*/
            request.getSession().removeAttribute("captcha");
        }
    }
}
