package com.ianw.security.securitydome5.config;/**
 * ClassName: RestTemplateConfig <br/>
 * Description: <br/>
 * date: 2019/8/7 14:37<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *@program: securityproject
 *@description:
 *@author: tao xujie
 *@create: 2019-08-07 14:37
 */

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);
        return factory;
    }
}