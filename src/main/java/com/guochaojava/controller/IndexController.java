package com.guochaojava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页相关
 *
 * @author guochao
 * @since 1.0.0
 */
@Controller
public class IndexController extends BaseController {

    private static final String VIEW_PREFIX = "index/";

    @RequestMapping({"/", "index"})
    public String index(HttpServletRequest request, Model model) {
        return VIEW_PREFIX + "index";
    }

    @RequestMapping("/welcome")
    public String main(Model model) {
        return VIEW_PREFIX + "welcome";
    }
}
