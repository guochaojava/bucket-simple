package com.guochaojava.util;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * @author guochao.
 * @since
 */
public class FreeMarkerConfigExtend extends FreeMarkerConfigurer {
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        Configuration cfg = this.getConfiguration();
        //shiro标签
        cfg.setSharedVariable("shiro", new ShiroTags());
        //防止页面输出数字,变成2,000
        cfg.setNumberFormat("#");
        //可以添加很多自己的要传输到页面的[方法、对象、值]
    }
}