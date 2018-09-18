package com.xyf.controller.manager;

import com.sun.imageio.plugins.common.ImageUtil;
import com.xyf.common.ImgUtils;
import com.xyf.common.MyResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadImg {
    private static final Logger logger = LoggerFactory.getLogger(UploadImg.class);

    @Value("${img.location}")
    private String filePath;

    //文件上传相关代码
    @RequestMapping(value = "web/api/manager/upload")
    @ResponseBody
    public MyResponse upload(@RequestParam("image-file") MultipartFile file) {
        if (file.isEmpty()) {
            return new MyResponse("文件为空",0);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        String realUrl = filePath + UUID.randomUUID() + suffixName;
        File dest = new File(realUrl);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            Map map = new HashMap<>();
            map.put("url",realUrl);
            return new MyResponse(map);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MyResponse("上传失败",0);
    }
}
