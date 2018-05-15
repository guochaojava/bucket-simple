package com.guochaojava.service;

import com.github.pagehelper.PageInfo;
import com.guochaojava.dto.query.UserQuery;
import com.guochaojava.model.User;

import java.util.Set;

public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    Set<String> selectRolesByLoginName(String loginName);

    Set<String> selectPermissionsByLoginName(String loginName);

    User selectUserByLoginName(String loginName);

    PageInfo<User> list(UserQuery query);

    int deleteByIds(String id);

    int updateStatusByIds(String id);

    int insertRoleUser(Integer roleId, Integer id);

    int updateRoleUser(Integer roleId, Integer id);
}
