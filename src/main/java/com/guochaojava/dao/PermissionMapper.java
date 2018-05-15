package com.guochaojava.dao;

import com.guochaojava.dto.query.PermissionQuery;
import com.guochaojava.model.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    List<Permission> list(PermissionQuery query);

    int deleteByIds(String[] tem);

    List<Permission> listNoPages();
}