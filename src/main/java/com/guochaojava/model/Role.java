package com.guochaojava.model;

import lombok.Data;

@Data
public class Role {
    /**
     * 对应数据库 role.id
     */
    private Integer id;

    /**
     * 对应数据库 role.name
     * 角色名称
     */
    private String name;

    /**
     * 对应数据库 role.code
     * 角色编码
     */
    private String code;


    /**
     * 前端展示需要
     */
    private String role;
}