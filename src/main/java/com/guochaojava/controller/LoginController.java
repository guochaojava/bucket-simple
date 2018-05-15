package com.guochaojava.controller;

import com.guochaojava.model.User;
import com.guochaojava.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录相关
 *
 * @author guochao
 * @since 1.0.0
 */
@Controller
public class LoginController extends BaseController {

    private static final String CURRENT_URL = CURRENT_PROJECT_NAME + "/login";

    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword());
            token.setRememberMe(user.isRememberMe());
            subject.login(token);

            return Result.buildOK("登录成功！").setUrl(getBaseUrl(request) + "/index");

        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError("用户名或密码错误");
        }
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public Object logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return Result.buildOK("登出成功！");
        } catch (Exception e) {
            logger.error(CURRENT_URL, e);
            return Result.buildError("系统异常");
        }
    }
}
