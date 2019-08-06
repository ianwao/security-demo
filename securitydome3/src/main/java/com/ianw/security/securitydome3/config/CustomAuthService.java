package com.ianw.security.securitydome3.config;/**
 * ClassName: CustomAuthService <br/>
 * Description: <br/>
 * date: 2019/8/5 14:19<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */


import com.ianw.security.securitydome3.domain.Permission;
import com.ianw.security.securitydome3.domain.Role;
import com.ianw.security.securitydome3.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 *@program: securityproject
 *@description:
 *@author: tao xujie
 *@create: 2019-08-05 14:19
 */
@Component
public class CustomAuthService {
    @Autowired
    PermissionRepository permissionRepository;

   public Boolean canAccess(HttpServletRequest request, Authentication authentication){
       //动态鉴权逻辑
       //1.先判断当前用户是否认证过
       Object principal = authentication.getPrincipal();
       if(principal==null||"anonymousUser".equals(principal)){
             return false;
       }
       //2.如果是匿名角色，ROLE_ANONYMOUS
       //3.动态鉴权逻辑
       //User
       //Role
       //Permission   uid  url 接口的对应关系
       String url = request.getRequestURI();
      Permission permission = permissionRepository.findByUrl(url);
       if(permission == null || CollectionUtils.isEmpty(permission.getRoles())){
           return false;
       }
       for (Role role :permission.getRoles()
            ) {
           if(CollectionUtils.isEmpty(authentication.getAuthorities())){
               return false;
           }
           for (GrantedAuthority authority : authentication.getAuthorities() ) {
               if(role.getName().equals(authority.getAuthority())){
                   return true;
               }
           }
       }
       return false;
   }
}