package com.guochaojava.model;

import lombok.Data;

import java.util.List;

@Data
public class Permission {
    /**
     * 对应数据库 permission.id
     */
    private Integer id;

    /**
     * 对应数据库 permission.name
     * 权限描述
     */
    private String name;

    /**
     * 对应数据库 permission.code
     * 权限编码
     */
    private String code;

    private Integer sort;

    private Integer level;

    private Integer pid;

    /**
     * 树结构需要
     */
    private List<Permission> sub;
}