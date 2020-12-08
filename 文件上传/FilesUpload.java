/**
 * Project Name:dt48_springMVC3
 * File Name:FilesUpload.java
 * Package Name:cn.java.utils
 * Date:上午11:54:12
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.java.utils;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

/**
 * Description: 此工具类可以同时上传多个文件<br/>
 * Date: 上午11:54:12 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
public class FilesUpload {
    /**
     * 
     * Description: 多文件上传<br/>
     *
     * @author dingP
     * @param request：类型为HttpServletRequest
     * @param files：类型为MultipartRequest
     */
    public static void uploadFiles(HttpServletRequest request, MultipartRequest files) {
        try {
            Map<String, MultipartFile> filesMap = files.getFileMap();
            Set<String> keySet = filesMap.keySet();
            for (String key : keySet) {
                MultipartFile file = filesMap.get(key);
                String originalFilename = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String path = request.getServletContext().getRealPath("/upload");
                String basePath = path.split("webapps")[0] + "webapps\\upload";
                File fileUpload = new File(basePath);
                if (!fileUpload.exists()) {// 经过此方法后,upload文件夹一定是存在的
                    fileUpload.mkdir();
                }
                // 保存文件
                String filePath = basePath + "\\" + uuid + originalFilename;
                file.transferTo(new File(filePath));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
