package com.ianw.security.securitydome5.service;


import com.ianw.security.securitydome5.domain.UserDomain;

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
