package com.guochaojava.controller;

import com.github.pagehelper.PageInfo;
import com.guochaojava.dto.query.RoleQuery;
import com.guochaojava.model.Role;
import com.guochaojava.service.RoleService;
import com.guochaojava.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author guochao
 * @since 1.0.0
 */
@Controller
public class RoleController extends BaseController {

    private static final String VIEW_PREFIX = "role/";
    private static final String CURRENT_URL = CURRENT_PROJECT_NAME + "/sys/role";

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/role")
    public String toRole(Model model) {
        return VIEW_PREFIX + "list";
    }

    @RequestMapping(value = "/role/list")
    @ResponseBody
    public Object roleList(RoleQuery query) {
        try {
            PageInfo<Role> pageInfo = roleService.list(query);
            Map<String, Object> map = new HashMap<>();
            map.put("list", pageInfo.getList());
            return Result.buildOK(pageInfo.getPages(), pageInfo.getTotal(), map);
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }

    }

    @RequestMapping(value = "/role/listNoPages")
    @ResponseBody
    public Object listNoPages(RoleQuery query) {
        try {
            List<Role> list = roleService.listNoPages(query);
            return Result.buildOK(list, "");
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }

    @RequestMapping(value = "/role/toAdd")
    public String toAdd(Model model) {
        return VIEW_PREFIX + "add";
    }

    @RequiresPermissions("role:add")
    @RequestMapping(value = "/role/add")
    @ResponseBody
    public Object roleAdd(Role role, @RequestParam(value = "roles[]") Integer[] roles, HttpServletRequest request) {
        List<Integer> permissions = Arrays.asList(roles);
        try {
            roleService.insertSelective(role);
            roleService.insertRolePermission(role.getId(), permissions);
            Map<String, Object> map = new HashMap<>();
            map.put("id", role.getId().toString());
            return Result.buildOK(map, "添加成功！");
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }

    @RequestMapping(value = "/role/toUpdate")
    public String toUpload(Integer id, Model model) {
        model.addAttribute("id", id);
        return VIEW_PREFIX + "edit";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/role/update")
    @ResponseBody
    public Object update(Role role, @RequestParam(value = "roles[]", required = false) Integer[] roles, HttpServletRequest request) {
        try {
            roleService.updateByPrimaryKeySelective(role);
            if (roles != null) {
                List<Integer> permissions = Arrays.asList(roles);
                roleService.updateRolePermission(role.getId(),permissions);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("id", role.getId().toString());
            return Result.buildOK(map, "更新成功！");
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/role/delete")
    @ResponseBody
    public Object roleDel(Integer[] id, HttpServletRequest request) {
        try {
            roleService.deleteByIds(id);
            return Result.buildOK("");
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }
}