package com.guochaojava.util;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 自定义freemarker视图处理类
 *
 * @author guochao
 * @since 1.0.0
 */
public class MyFreeMarkerView extends FreeMarkerView {
    private static final String CONTEXT_PATH = "base";
    private static String documentDomain = "127.0.0.1";
    private static final int ROOT_PORT = 80;

    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        // base 访问域名
        String appContext = request.getContextPath();
        StringBuilder basePath = new StringBuilder();
        basePath.append(request.getScheme()).append("://");
        basePath.append(request.getServerName());
        if (request.getServerPort() != ROOT_PORT) {
            basePath.append(":").append(request.getServerPort());
        }
        basePath.append(appContext);
        model.put(CONTEXT_PATH, basePath.toString());

        model.put("documentDomain", documentDomain);

        super.exposeHelpers(model, request);
    }
}
