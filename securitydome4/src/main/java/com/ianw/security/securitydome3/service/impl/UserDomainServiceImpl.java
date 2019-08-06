package com.ianw.security.securitydome3.service.impl;/**
 * ClassName: UserDomainServiceImpl <br/>
 * Description: <br/>
 * date: 2019/8/2 16:21<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */


import com.ianw.security.securitydome3.domain.UserDomain;
import com.ianw.security.securitydome3.repository.UserDomainRepository;
import com.ianw.security.securitydome3.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@program: securityproject
 *@description:
 *@author: tao xujie
 *@create: 2019-08-02 16:21
 */
@Service
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    private UserDomainRepository userDomainRepository;

    @Override
    public UserDomain findByUserName(String username) {
        return userDomainRepository.findByUsername(username);
    }
}