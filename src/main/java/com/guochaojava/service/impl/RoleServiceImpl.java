package com.guochaojava.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guochaojava.dao.RoleMapper;
import com.guochaojava.dto.query.RoleQuery;
import com.guochaojava.model.Permission;
import com.guochaojava.model.Role;
import com.guochaojava.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guochao.
 * @since
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Role record) {
        return dao.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Role> list(RoleQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Role> list = dao.list(query);
        PageInfo<Role> pageInfo = new PageInfo<>(list);

        List<Role> roleView = new ArrayList<>();
        for (Role menu : list) {
            Role rv = new Role();
            BeanUtils.copyProperties(menu, rv);
            //处理权限
            List<Role> roles = dao.selectPermissionIdsByRoleId(rv.getId());
            String rolestr = "";
            for (Role r : roles) {
                rolestr += r.getId() + ",";
            }
            if (rolestr != "") {
                rv.setRole(rolestr.substring(0, rolestr.length() - 1));
            } else {
                rv.setRole(rolestr);
            }
            roleView.add(rv);
        }
        PageInfo<Role> pageInfoView = new PageInfo<>(roleView);
        pageInfoView.setPages(pageInfo.getPages());

        return pageInfoView;
    }

    @Override
    public List<Role> listNoPages(RoleQuery query) {
        List<Role> list = dao.list(query);
        return list;
    }

    @Override
    public int deleteByIds(Integer[] id) {
        for(Integer integer:id){
            dao.deletebyRoleId(integer);
        }
        return dao.deleteByIds(id);
    }

    @Override
    public int insertRolePermission(Integer id, List<Integer> permissions) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", id);
        map.put("permissions", permissions);
        return dao.insertRolePermission(map);
    }

    @Override
    public int updateRolePermission(Integer id, List<Integer> permissions) {
        //删除
        dao.deletebyRoleId(id);
        //更新
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", id);
        map.put("permissions", permissions);
        return dao.updateRolePermission(map);
    }
}