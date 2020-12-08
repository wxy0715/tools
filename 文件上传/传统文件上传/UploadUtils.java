/**
 * Project Name:dt32_lesson50
 * File Name:UploadUtils.java
 * Package Name:cn.java.utils
 * Date:2017年4月7日上午9:08:38
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.java.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Description: <br/>
 * Date: 2017年4月7日 上午9:08:38 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
public class UploadUtils {

    /**
     * 
     * Description: 文件上传的工具方法(此方法可以同时上传N个文件)<br/>
     * 
     * @author dingP
     * @param request:请求对象，HttpServletRequest
     * @param uploadDirectory:上传的文件保存的文件夹，不要带“/”，例如上传的文件保存到upload下，传"upload"
     * @return:Map<String,Object>：封装的是普通字段
     */
    public static Map<String, Object> UploadFile(HttpServletRequest request, String uploadDirectory) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            ServletContext context = request.getServletContext();
            // 1、首先得到ServletFileUpload，是文件上传的核心组件。它能够将request中的每一个属性字段都封装成FileItem对象
            DiskFileItemFactory fatory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(fatory);
            // 设置文件上传的大小(单个文件上传的大小，和多个文件上传的大小)
            // 动态获取单个文件大小
            String singleFileSize = context.getInitParameter("singleFileSize");
            String multiFilesSize = context.getInitParameter("multiFilesSize");
            sfu.setFileSizeMax(Long.parseLong(singleFileSize));// 设置单个文件的大小
            sfu.setSizeMax(Long.parseLong(multiFilesSize));// 设置多个文件的大小
            // 2、需要判断当前的FileItem是文件还是普通字段
            List<FileItem> fileItemLists = sfu.parseRequest(request);
            // 3、如果是文件的话，就保存到服务器中
            for (FileItem fileItem : fileItemLists) {
                // 判断当前拿到的是否是文件
                boolean flag = fileItem.isFormField();// flag=true代表当前获取的是普通字段(username);flag=false代表当前的FileItem封装的是文件
                if (flag) {// 普通字段
                    // String username = request.getParameter("username");
                    String field1 = fileItem.getFieldName();
                    // 动态获取web.xml文件中的编码配置
                    String encode = context.getInitParameter("encode");
                    String value = fileItem.getString(encode);
                    System.out.println("=================================" + field1 + "=" + value);
                    // 将普通字段的值保存到Map集合中去
                    params.put(field1, value);
                } else {// 文件，需要将文件保存到当前工程的upload文件夹中
                    System.out.println("*****************fileItem.getName()======" + fileItem.getName());
                    // 首先拿到上传的文件
                    InputStream ins = fileItem.getInputStream();// 获取文件到servlet的流
                    // 将上传过来的文件保存到upload这个文件夹下面去
                    // 动态获取文件夹的绝对路径
                    // 根据系统当前的时间，返回一个yyyyMMdd的文件名
                    long cTime = System.currentTimeMillis();
                    Date currentDate = new Date(cTime);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String yyyyMMdd = sdf.format(currentDate);
                    String realPath = context.getRealPath("/" + uploadDirectory) + "\\" + yyyyMMdd;// D://tomcat//webapp//工程名/upload//20170407
                    File file = new File(realPath);
                    // 判断指定路径的文件是否存在
                    boolean isExists = file.exists();
                    if (isExists == false) {// 如果不存在则创建
                        file.mkdirs();
                    }
                    // 动态获取拓展名
                    String fileName = fileItem.getName();// 1.png
                    String extension = fileName.split("\\.")[1];
                    // 动态的生成全球不重复的名字
                    String uuid = UUID.randomUUID().toString();// 网卡号+时间戳
                    FileOutputStream fos = new FileOutputStream(realPath + "\\" + uuid + "." + extension);
                    // 将数据写入到磁盘上面去
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = ins.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                    // 关闭各种流
                    ins.close();
                    fos.close();
                    // System.out.println("context.getRealPath(/upload)==========="
                    // + realPath);
                }
            }
        } catch (Exception e) {
            params.put("msg", "文件上传失败，请您检查一下文件大小");
            e.printStackTrace();
        }
        return params;
    }
}
