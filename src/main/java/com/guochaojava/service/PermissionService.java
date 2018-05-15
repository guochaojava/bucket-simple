package com.guochaojava.service;

import com.github.pagehelper.PageInfo;
import com.guochaojava.dto.query.PermissionQuery;
import com.guochaojava.model.Permission;

import java.util.List;

public interface PermissionService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    PageInfo<Permission> list(PermissionQuery query);

    int deleteByIds(String id);

    List<Permission> listNoPages();
}
