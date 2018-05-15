package com.guochaojava.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    /**
     * 对应数据库 user.id
     */
    private Integer id;

    /**
     * 对应数据库 user.login_name
     * 登录名
     */
    private String loginName;

    /**
     * 对应数据库 user.password
     * 密码
     */
    private String password;

    /**
     * 对应数据库 user.nick_name
     * 用户昵称
     */
    private String nickName;

    /**
     * 对应数据库 user.telephone
     * 手机号
     */
    private String telephone;

    /**
     * 对应数据库 user.email
     * 邮箱
     */
    private String email;

    /**
     * 对应数据库 user.create_time
     * 创建时间
     */
    private String createTime;

    /**
     * 对应数据库 user.last_login_time
     * 最后登录时间
     */
    private String lastLoginTime;

    /**
     * 对应数据库 user.status
     * 1:有效，0:禁止登录
     */
    private Integer status;

    private boolean rememberMe;

    private Integer roleId;

    private String roleName;
}