package com.guochaojava.controller;

import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageInfo;
import com.guochaojava.dto.query.UserQuery;
import com.guochaojava.model.User;
import com.guochaojava.service.UserService;
import com.guochaojava.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 人员管理
 *
 * @author guochao
 * @since 1.0.0
 */
@Controller
public class UserController extends BaseController {

    private static final String VIEW_PREFIX = "user/";
    private static final String CURRENT_URL = CURRENT_PROJECT_NAME + "/user";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user")
    public String toUser() {
        return VIEW_PREFIX + "list";
    }

    @RequestMapping(value = "/user/list")
    @ResponseBody
    public Object userList(UserQuery query) {
        try {
            PageInfo<User> pageInfo = userService.list(query);
            Map<String, Object> map = new HashMap<>();
            map.put("list", pageInfo.getList());
            return Result.buildOK(pageInfo.getPages(), pageInfo.getTotal(), map);
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }

    }

    @RequiresPermissions({"user:add", "user:update"})
    @RequestMapping(value = "/user/add")
    @ResponseBody
    public Object userAdd(User user, HttpServletRequest request) {
        try {
            if (user.getId() != null) {
                if (user.getPassword() != "" && user.getPassword() != null) {
                    user.setPassword(SecureUtil.md5(user.getPassword()));
                }
                userService.updateByPrimaryKeySelective(user);
                //更新role
                userService.updateRoleUser(user.getRoleId(), user.getId());
                return Result.buildOK("修改成功");
            } else {
                user.setPassword(SecureUtil.md5(user.getPassword()));
                userService.insertSelective(user);
                // 处理role
                userService.insertRoleUser(user.getRoleId(), user.getId());
                Map<String, Object> map = new HashMap<>();
                map.put("id", user.getId().toString());
                return Result.buildOK(map, "添加成功");
            }
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/user/delete")
    @ResponseBody
    public Object userDel(String id) {
        try {
            userService.deleteByIds(id);
            return Result.buildOK("删除成功！");
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/user/status")
    @ResponseBody
    public Object userStatus(String id) {
        try {
            userService.updateStatusByIds(id);
            return Result.buildOK("更新成功！");
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError();
        }
    }
}