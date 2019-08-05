package com.ianw.security.securitydome3.domain;/**
 * ClassName: Role <br/>
 * Description: <br/>
 * date: 2019/8/5 9:33<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *@program: securityproject
 *@description:  角色表
 *@author: tao xujie
 *@create: 2019-08-05 09:33
 */

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;//主键.
    private String name;//角色名称.
    private String description;//角色描述.

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}