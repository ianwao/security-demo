package com.ianw.security.securitydome3.service;


import com.ianw.security.securitydome3.domain.UserDomain;

/**
 * ClassName: UserDomainService <br/>
 * Description: <br/>
 * date: 2019/8/2 16:21<br/>
 *
 * @author 72733<br />
 * @since JDK 1.8
 */
public interface UserDomainService {

    UserDomain findByUserName(String username);
}
