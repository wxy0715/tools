/**
 * Project Name:dt48_springMVC3
 * File Name:Test1Controller.java
 * Package Name:cn.java.controller.app
 * Date:上午8:41:45
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.java.controller.app;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import cn.java.entity.UserValidator;
import cn.java.utils.FilesUpload;

/**
 * Description: <br/>
 * Date: 上午8:41:45 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
@Controller
@RequestMapping(value = "/upload/")
public class Test2Controller {
    /**
     * 
     * Description: 单文件上传<br/>
     *
     * @author dingP
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("singleFileUpload")
    public void singleFileUpload(@RequestParam(name = "bigHeadImage") MultipartFile file, HttpServletRequest request)
            throws Exception {
        // 1、getOriginalFilename():获取上传文件的文件名
        String originalFilename = file.getOriginalFilename();
        // 2、<input type="file" name="bigHeadImage"></p>
        // getName()：获取的是file控件中name的属性值，此处的属性值为bigHeadImage
        String name = file.getName();
        System.out.println("file.getOriginalFilename()=" + originalFilename);
        System.out.println("file.getName()=" + name);
        // 3、将上传的文件保存到指定的目录下
        // path =
        // D:\\software\\eclipse_oxygen_x64_4.7\\apache-tomcat-9.0.0.M22\\webapps\\dt48_springMVC3\\upload
        String uuid = UUID.randomUUID().toString();
        String path = request.getServletContext().getRealPath("/upload");
        // File filePath = new File(path + "\\" + uuid + originalFilename);
        File filePath = new File("D:\\software\\eclipse_oxygen_x64_4.7\\apache-tomcat-9.0.0.M22\\webapps\\uploads\\"
                + uuid + originalFilename);
        file.transferTo(filePath);
    }

    @RequestMapping(value = "getUploadPath")
    public void getUploadPath(HttpServletRequest request) {
        // String path =
        // request.getRealPath("/upload");pageContext--->request--->session---->application(ServletContext)
        ServletContext sc = request.getServletContext();
        String path = sc.getRealPath("/upload");
        System.out.println(path);
    }

    /**
     * 
     * Description: UUID：可以生成不重复的序列号<br/>
     *
     * @author dingP
     */
    @Test
    public void testUUID() {
        // 网卡号+时间戳
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

    /**
     * 
     * Description: 多文件上传<br/>
     *
     * @author dingP
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("mutipleFileUpload")
    public void mutipleFileUpload(MultipartRequest files, HttpServletRequest request) throws Exception {
        FilesUpload.uploadFiles(request, files);
    }

    @RequestMapping("testValidator")
    public void testValidator(@Valid UserValidator user, BindingResult errorResult) {
        boolean flag = errorResult.hasErrors();
        System.out.println("是否有错误：" + flag);
        List<FieldError> errorList = errorResult.getFieldErrors();
        for (FieldError error : errorList) {
            System.out.println(error.getField() + "=" + error.getDefaultMessage());
        }
    }

    // @Scheduled(fixedRate = 1000)
    // @Scheduled(cron = "0/2 39 1 * * ?")
    @Scheduled(cron = "0/2 49 13 * * ?")
    public void oneTask() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间为：" + sdf.format(d));
    }
}
