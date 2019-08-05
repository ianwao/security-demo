package com.ianw.security.securitydome3.extend;/**
 * ClassName: CustomUserDetailService <br/>
 * Description: <br/>
 * date: 2019/8/2 16:24<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */


import com.ianw.security.securitydome3.domain.Role;
import com.ianw.security.securitydome3.domain.UserDomain;
import com.ianw.security.securitydome3.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *@program: securityproject
 *@description:
 *@author: tao xujie
 *@create: 2019-08-02 16:24
 */

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDomainService userDomainService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDomain userDomain = userDomainService.findByUserName(username);
        if(userDomain == null) {
            throw new UsernameNotFoundException("not found");
        }
        //定义权限列表.
        List<GrantedAuthority> authorities = new ArrayList<>();
        //用户可以访问的资源名称（或者说用户所拥有的权限)注意：必须"ROLE_"开头
        for(Role role:userDomain.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        User userDetails = new User(userDomain.getUsername(),userDomain.getPassword(), authorities);
        return userDetails;
    }
}