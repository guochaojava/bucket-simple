package com.guochaojava.controller;

import com.github.pagehelper.PageInfo;
import com.guochaojava.dto.query.PermissionQuery;
import com.guochaojava.model.Permission;
import com.guochaojava.service.PermissionService;
import com.guochaojava.util.Result;
import com.guochaojava.util.TreeObjectUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限管理
 *
 * @author guochao
 * @since 1.0.0
 */
@Controller
public class PermissionController extends BaseController {

    private static final String VIEW_PREFIX = "permission/";
    private static final String CURRENT_URL = CURRENT_PROJECT_NAME + "/permission";

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/permission")
    public String toPermission() {
        return VIEW_PREFIX + "list";
    }

    @RequestMapping(value = "/permission/list")
    @ResponseBody
    public Object permissionList(PermissionQuery query) {
        try {
            PageInfo<Permission> pageInfo = permissionService.list(query);
            Map<String, Object> map = new HashMap<>();
            map.put("list", pageInfo.getList());
            return Result.buildOK(pageInfo.getPages(), pageInfo.getTotal(), map);
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }

    @RequestMapping(value = "/permission/listNoPages")
    @ResponseBody
    public Object listNoPages() {
        try {
            List<Permission> list = permissionService.listNoPages();
            TreeObjectUtil mu = new TreeObjectUtil();
            List<Permission> result = mu.getChildMenuObjects(list);
            Map<String, Object> map = new HashMap<>();
            map.put("list", result);
            return Result.buildOK(map, "success");
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }

    @RequiresPermissions({"permission:add", "permission:update"})
    @RequestMapping(value = "/permission/add")
    @ResponseBody
    public Object permissionAdd(Permission permission) {
        try {
            if (permission.getId() != null) {
                permissionService.updateByPrimaryKeySelective(permission);
                return Result.buildOK("更新成功");
            } else {
                permissionService.insertSelective(permission);
                Map<String, Object> map = new HashMap<>();
                map.put("id", permission.getId().toString());
                return Result.buildOK(map, "添加成功");
            }
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }

    @RequiresPermissions("permission:delete")
    @RequestMapping(value = "/permission/delete")
    @ResponseBody
    public Object permissionDel(String id) {
        try {
            permissionService.deleteByIds(id);
            return Result.buildOK("删除成功！");
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }
}