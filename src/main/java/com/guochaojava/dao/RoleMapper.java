package com.guochaojava.dao;

import com.guochaojava.dto.query.RoleQuery;
import com.guochaojava.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    List<Role> list(RoleQuery query);

    int deleteByIds(Integer[] id);

    int insertRolePermission(Map<String, Object> map);

    int updateRolePermission(Map<String, Object> map);

    List<Role> selectPermissionIdsByRoleId(Integer id);

    int deletebyRoleId(Integer id);
}