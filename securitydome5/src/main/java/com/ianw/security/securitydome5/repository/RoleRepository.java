package com.ianw.security.securitydome5.repository;


import com.ianw.security.securitydome5.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName: RoleRepository <br/>
 * Description: <br/>
 * date: 2019/8/5 9:39<br/>
 *
 * @author 72733<br />
 * @since JDK 1.8
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
}
