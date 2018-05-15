package com.guochaojava.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guochaojava.dao.PermissionMapper;
import com.guochaojava.dto.query.PermissionQuery;
import com.guochaojava.model.Permission;
import com.guochaojava.model.User;
import com.guochaojava.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guochao.
 * @since
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Permission record) {
        return dao.insertSelective(record);
    }

    @Override
    public Permission selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Permission record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Permission> list(PermissionQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Permission> list = dao.list(query);
        PageInfo<Permission> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int deleteByIds(String id) {
        String[] tem = id.split(",");
        return dao.deleteByIds(tem);
    }

    @Override
    public List<Permission> listNoPages() {
        return dao.listNoPages();
    }
}