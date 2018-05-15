package com.guochaojava.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guochaojava.dao.UserMapper;
import com.guochaojava.dto.query.UserQuery;
import com.guochaojava.model.User;
import com.guochaojava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author guochao.
 * @since
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(User record) {
        return dao.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public Set<String> selectRolesByLoginName(String loginName) {
        return dao.selectRolesByLoginName(loginName);
    }

    @Override
    public Set<String> selectPermissionsByLoginName(String loginName) {
        return dao.selectPermissionsByLoginName(loginName);
    }

    @Override
    public User selectUserByLoginName(String loginName) {
        return dao.selectUserByLoginName(loginName);
    }

    @Override
    public PageInfo<User> list(UserQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<User> list = dao.list(query);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int deleteByIds(String id) {
        String[] tem = id.split(",");
        return dao.deleteByIds(tem);
    }

    @Override
    public int updateStatusByIds(String id) {
        String[] tem = id.split(",");
        return dao.updateStatusByIds(tem);
    }

    @Override
    public int insertRoleUser(Integer roleId, Integer id) {
        return dao.insertRoleUser(roleId,id);
    }

    @Override
    public int updateRoleUser(Integer roleId, Integer id) {
        return dao.updateRoleUser(roleId,id);
    }
}