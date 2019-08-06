package com.ianw.security.securitydome3.domain;/**
 * ClassName: Permission <br/>
 * Description: <br/>
 * date: 2019/8/5 14:32<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import javax.persistence.*;
import java.util.List;

/**
 *@program: securityproject
 *@description:
 *@author: tao xujie
 *@create: 2019-08-05 14:32
 */

@Entity
public class Permission {
    @Id
    @GeneratedValue
    private long pid;//主键.

    //private String name;//权限名称.
    //private String description;//权限描述.

    private String url;//授权链接

    //private long pid;//父节点id.

    // 角色 - 权限是多对多的关系
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="RolePermission",joinColumns= {@JoinColumn(name="pid")} , inverseJoinColumns= {@JoinColumn(name="rid")})
    private List<Role> roles;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }
    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}