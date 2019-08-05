package com.ianw.security.securitydome3.extend;/*
package com.ianw.security.securitydemo2.extend;*/
/**
 * ClassName: DataInit <br/>
 * Description: <br/>
 * date: 2019/8/2 16:35<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 *//*


import com.ianw.security.securitydemo2.domain.UserDomain;
import com.ianw.security.securitydemo2.repository.UserDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

*/
/**
 *@program: securityproject
 *@description:  初始化user domain表的数据
 *@author: tao xujie
 *@create: 2019-08-02 16:35
 *//*


@Service
public class DataInit {
    @Autowired
    private UserDomainRepository userDomainRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostConstruct

    public void dataInit() {
        UserDomain admin = new UserDomain();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setRole(UserDomain.Role.ROLE_ADMIN);
        userDomainRepository.save(admin);

        UserDomain user = new UserDomain();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRole(UserDomain.Role.ROLE_USER);
        userDomainRepository.save(user);
    }
}*/
