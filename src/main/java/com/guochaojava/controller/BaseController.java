package com.guochaojava.controller;

import cn.hutool.core.util.ImageUtil;
import com.guochaojava.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Calendar;


/**
 * 基础控制器
 *
 * @author guochao
 * @since 1.0.0
 */
public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String CURRENT_PROJECT_NAME = "/bucket";

    private static final int ROOT_PORT = 80;


    /**
     * 获取基本路径 例如：http://localhost:8080/admin-model @client @client
     *
     * @param request
     * @return 路径
     */
    public String getBaseUrl(HttpServletRequest request) {
        String appContext = request.getContextPath();
        StringBuilder basePath = new StringBuilder();
        basePath.append(request.getScheme()).append("://");
        basePath.append(request.getServerName());
        if (request.getServerPort() != ROOT_PORT) {
            basePath.append(":").append(request.getServerPort());
        }
        basePath.append(appContext);
        return basePath.toString();
    }


    /**
     * 保存文件
     *
     * @param file     MultipartFile类型文件变量
     * @param type     保存文件类型 eg: head 头像 ; article 文章
     * @param fileName 保存文件名
     * @return
     * @throws Exception
     */
    public String saveFiles(MultipartFile file, String type, String fileName) throws Exception {
        // 基本路径 用于生成文件
        String basePath = "";
        // 相对路径 用于返回路径并持久化
        String path = "";
        // eg: \2017\4\
        String classifyPath = getUploadPath();
        // 获取当前操作系统
        String os = System.getProperty("os.name");
        // 用于判断是windows还是linux
        if (os.toLowerCase().startsWith("win")) {
            // windows   D:\\upload
            basePath = CommonUtils.findPropertiesKey("wUploadFilePath");
        } else {
            // linux   /home/upload
            basePath = CommonUtils.findPropertiesKey("lUploadFilePath");
        }

        if ("file".equals(type)) {
            path = "/file" + classifyPath;
        }

        File targetFile = new File(basePath + path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 保存
        file.transferTo(targetFile);
        // 获取文件大小：  单位 b
        long fileSize = file.getSize();
        String[] tem = fileName.split("\\.");
        if ("jpg".equalsIgnoreCase(tem[1]) || "png".equalsIgnoreCase(tem[1]) || "gif".equalsIgnoreCase(tem[1])
                || "jepg".equalsIgnoreCase(tem[1])) {
            if (fileSize > 300000) {
                //缩放文件
                ImageUtil.scale(targetFile, targetFile, (float) 1);
            }
        }

        return "/upload" + path + "/" + fileName;
    }

    /**
     * 获取上传路径
     *
     * @return String
     */
    public static String getUploadPath() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = (calendar.get(Calendar.MONTH) + 1);
        String path = "/" + year + "/" + month;
        return path;
    }
}
