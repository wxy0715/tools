package com.longersec.blj.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("file")
public class UploadFileController {

    @RequestMapping("/upload")
    public String fileload(MultipartFile file, HttpServletRequest request) {

        //获取文件名称
        String fileName = file.getOriginalFilename();

        //设置上传路径
        String path = "/Users/liuchengwen/Desktop/IDEAProject/blj_new/src/file/";

        //文件上传
        File f = new File(path);

        //判断路径是否存在，不存在则创建
        if(!f.exists()){
            f.mkdirs();
        }
        //判断上传文件是否为空
        if(!file.isEmpty()){
            try {
                FileOutputStream fos = new FileOutputStream(path + fileName);
                InputStream in = file.getInputStream();
                int a = 0;
                if((a = in.read())!=-1){
                    fos.write(a);
                }
                in.close();
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        String address = path+fileName;
        System.out.println(address);

        return address;
    }
}