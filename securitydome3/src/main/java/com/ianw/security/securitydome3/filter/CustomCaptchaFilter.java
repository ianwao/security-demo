package com.ianw.security.securitydome3.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class CustomCaptchaFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(CustomCaptchaFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain filterChain) throws IOException, ServletException {
        logger.info("CustomFilter,servletRequest={},servletResponse={}",
                servletRequest.getParameter("captcha"), servletResponse);


        filterChain.doFilter(servletRequest, servletResponse);
    }
}