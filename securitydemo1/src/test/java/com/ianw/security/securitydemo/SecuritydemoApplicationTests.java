package com.ianw.security.securitydemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecuritydemoApplicationTests {

    private final static Logger LOGGER = LoggerFactory.getLogger(SecuritydemoApplicationTests.class);

    @Test
    public static void main(String[] args) {
        String u = "user";
        String p = "123456";
        byte[] basicbytes = Base64.getEncoder().encode((u+":"+p).getBytes());
        System.out.println(basicbytes);
    }

}
