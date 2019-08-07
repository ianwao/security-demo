package com.ianw.security.securitydome5.repository;/**
 * ClassName: UserRepository <br/>
 * Description: <br/>d
 * date: 2019/8/2 16:18<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */


import com.ianw.security.securitydome5.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *@program: securityproject
 *@description:
 *@author: tao xujie
 *@create: 2019-08-02 16:18
 */
public interface UserDomainRepository extends JpaRepository<UserDomain,Long> {

     UserDomain findByUsername(String username);

}