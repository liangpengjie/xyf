package com.xyf.controller.manager;

import com.xyf.common.MyResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@CrossOrigin
@RestController
public class UploadImg {
    private static final Logger logger = LoggerFactory.getLogger(UploadImg.class);

    @Value("${img.location}")
    private String filePath;
    private static final String IMGURL = "http://122.114.254.63:9090/imgs/";

    //文件上传相关代码
    @RequestMapping(value = "web/api/manager/upload")
    @ResponseBody
    public MyResponse upload(@RequestBody MultipartFile file) {
        if (file.isEmpty()) {
            return new MyResponse("文件为空",0);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        String realUrl = filePath + uuid + suffixName;
        logger.info("上传的路径为：" + realUrl);
        File dest = new File(realUrl);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            Map map = new HashMap<>();
            map.put("url",IMGURL+uuid+suffixName);
            return new MyResponse(map);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MyResponse("上传失败",0);
    }

}
