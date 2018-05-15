package com.guochaojava.service;

import com.github.pagehelper.PageInfo;
import com.guochaojava.dto.query.RoleQuery;
import com.guochaojava.model.Role;

import java.util.List;

public interface RoleService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    PageInfo<Role> list(RoleQuery query);

    List<Role> listNoPages(RoleQuery query);

    int deleteByIds(Integer[] id);

    int insertRolePermission(Integer id, List<Integer> permissions);

    int updateRolePermission(Integer id, List<Integer> permissions);
}
