package com.guochaojava.dao;


import com.guochaojava.dto.query.UserQuery;
import com.guochaojava.model.User;

import java.util.List;
import java.util.Set;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    User selectUserByLoginName(String loginName);

    Set<String> selectPermissionsByLoginName(String loginName);

    Set<String> selectRolesByLoginName(String loginName);

    int deleteByIds(String[] tem);

    int updateStatusByIds(String[] tem);

    List<User> list(UserQuery query);

    int insertRoleUser(Integer roleId, Integer id);

    int updateRoleUser(Integer roleId, Integer id);
}