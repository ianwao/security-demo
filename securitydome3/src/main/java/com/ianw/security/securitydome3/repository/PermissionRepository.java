package com.ianw.security.securitydome3.repository;

import com.ianw.security.securitydome3.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName: PermissionRepository <br/>
 * Description: <br/>
 * date: 2019/8/5 14:37<br/>
 *
 * @author 72733<br />
 * @since JDK 1.8
 */

public interface PermissionRepository extends JpaRepository<Permission,Long> {

  Permission findByUrl(String url);

}
