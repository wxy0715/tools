package com.longersec.blj.web;

import com.longersec.blj.dao.ProtocolDao;
import com.longersec.blj.dao.RoleDao;
import com.longersec.blj.domain.*;
import com.longersec.blj.service.DeviceAccountService;
import com.longersec.blj.service.DeviceService;
import com.longersec.blj.service.GroupService;
import com.longersec.blj.service.UserService;
import com.longersec.blj.utils.MultipartFileToFile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserService userService;
    @Autowired
    private ProtocolDao protocolDao;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceAccountService deviceAccountService;

    @RequestMapping("/group")
    @ResponseBody
    public JSONObject fileUpload(HttpServletRequest request,
                                 @RequestParam("file_data")MultipartFile fileUp, HttpServletResponse response, @RequestParam("type") Integer type) throws Exception {
        JSONObject result = new JSONObject();
        result.put("success", true);
        int t = type;
        if(fileUp==null || fileUp.isEmpty()){
            result.put("success", false);
        }
        try{
            File file = MultipartFileToFile.multipartFileToFile(fileUp);
            System.out.println(file);
            List<String> list = importCsv(file);
            ArrayList<Group> listGroup  = new ArrayList<>();
            for(int i=1;i<list.size();i++){
                String[] temp = list.get(i).split(",");
                Group group = new Group();
                group.setName(temp[0]);
                group.setType(type);
                Group _group = groupService.checkname(group);
                group.setDesc(temp[1]);
                group.setType(type);
                if (_group==null){
                    listGroup.add(group);
                }

            }
            if (result.getBoolean("success")){
                if (listGroup.size()!=0){
                    boolean b = groupService.insertMore(listGroup);
                    result.put("success", b?true:false);
                    if (list.size()-listGroup.size()==1){
                        result.put("msg", "导入成功");
                    }else {
                        result.put("msg", "部分成功");
                    }
                }else {
                    result.put("success", false);
                }
            }

        }catch (Exception e){
            result.put("success", false);
        }

        return result;
    }


    @RequestMapping("/user")
    @ResponseBody
    public JSONObject fileUploaduser(HttpServletRequest request,
                                 @RequestParam("file_data")MultipartFile fileUp, HttpServletResponse response, @RequestParam("type") Integer type) throws Exception {
        JSONObject result = new JSONObject();
        result.put("success", true);
        int t = type;
        if(fileUp==null || fileUp.isEmpty()){
            result.put("success", false);
        }
        try{
            File file = MultipartFileToFile.multipartFileToFile(fileUp);
            System.out.println(file);
            List<String> list = importCsv(file);
            ArrayList<User> users  = new ArrayList<>();
            String[] header = list.get(0).split(",");
            if (header.length!=9){
                result.put("success", false);
            }
            if (result.getBoolean("success") ){
                for(int i=1;i<list.size();i++){
                String[] temp1 = list.get(i).split(",");
                String[] temp = insert(temp1," "," "," "," ");
                System.out.println(temp.length);
                User user = new User();
                User user1 = userService.checkLogin(temp[0]);
                user.setUsername(temp[0]);
                user.setRealname(temp[1]);
                Group group = groupService.selectByname(temp[2],0);
                if (group!=null){
                    user.setGroupid(group.getId());
                }
                Role role = roleDao.checkname(temp[3]);
                if (role!=null){
                    user.setRole_id(role.getId());
                }
                user.setPassword(temp[4]);
                if (!temp[5].equals(" ")){
                    user.setEmail(temp[5]);
                }else {
                    user.setEmail(null);
                }
                if (!temp[6].equals(" ")){
                    user.setEmail(temp[6]);
                }else {
                    user.setEmail(null);
                }
                if (!temp[7].equals(" ")){
                    user.setEmail(temp[7]);
                }else {
                    user.setEmail(null);
                }
                if (!temp[8].equals(" ")){
                    user.setEmail(temp[8]);
                }else {
                    user.setEmail(null);
                }

                if (user1==null && role!=null && group!=null){
                    users.add(user);
                }

            }


                if (users.size()!=0){
                    boolean b = userService.insertMore(users);
                    result.put("success", b?true:false);
                    if (list.size()-users.size()==1){
                        result.put("msg", "导入成功");
                    }else {
                        result.put("msg", "部分成功");
                    }
                }else {
                    result.put("success", false);
                }
            }

        }catch (Exception e){
            result.put("success", false);
        }

        return result;
    }


    @RequestMapping("/device")
    @ResponseBody
    public JSONObject fileUploaddevice(HttpServletRequest request,@RequestParam("file_data")MultipartFile fileUp, HttpServletResponse response, @RequestParam("type") Integer type) throws Exception {
        JSONObject result = new JSONObject();
        result.put("success", true);
        int t = type;
        if(fileUp==null || fileUp.isEmpty()){
            result.put("success", false);
        }
        try{
            File file = MultipartFileToFile.multipartFileToFile(fileUp);
            System.out.println(file);
            List<String> list = importCsv(file);
            ArrayList<Device> devices  = new ArrayList<>();
            for(int i=1;i<list.size();i++){
                String[] temp1 = list.get(i).split(",");
                String[] temp = insert(temp1," "," ");
                System.out.println(temp.length);
                Device device = new Device();
                Device checkname = deviceService.checkname(temp[0]);
                if (checkname==null){
                    device.setName(temp[0]);
                }
                Device checkip = deviceService.checkip(temp[1]);
                if (checkip==null){
                    device.setIp(temp[1]);
                }
                device.setOs_type(Integer.parseInt(temp[2]));
                Group group = groupService.selectByname(temp[3],1);
                if (group!=null){
                    device.setGroupid(group.getId());
                }
                device.setDescription(temp[4]);
                System.out.println(temp[5]);
                System.out.println(temp[6]);
                if (temp[5]==""||temp[6]==""){
                    device.setLogin_method(1);
                    device.setSuper_account(temp[5]);
                    device.setSuper_password(temp[6]);
                }else {
                    device.setLogin_method(0);
                    device.setSuper_account(temp[5]);
                    device.setSuper_password(temp[6]);
                }

                Protocol protocol = protocolDao.getByName(temp[7]);
                if (protocol!=null){
                    device.setProtocol_id(protocol.getId());
                }
                System.out.println(temp[8]);
                if (!temp[8].equals("")){
                    device.setPort(Integer.parseInt(temp[8]));
                }

                System.out.println(temp[9]);
                if (!temp[9].equals(" ")){
                    device.setSsh_key(Integer.parseInt(temp[9]));
                }else {
                    device.setSsh_key(0);
                }
                if (protocol!=null && group!=null && checkname==null && checkip == null &&  !temp[8].equals("")){
                    devices.add(device);
                }
            }
            if (result.getBoolean("success") ){
                if (devices.size()!=0){
                    boolean b = deviceService.insertMore(devices);
                    boolean c = deviceAccountService.insertMore(devices);
                    if (b==true&&c==true){
                        result.put("success", true);
                    }else {
                        result.put("success", false);
                    }
                }
            }

        }catch (Exception e){
            result.put("success", false);
        }

        return result;
    }


    // 往字符串数组追加新数据
    private static String[] insert(String[] arr, String... str) {
        int size = arr.length; // 获取原数组长度
        int newSize = size + str.length; // 原数组长度加上追加的数据的总长度

        // 新建临时字符串数组
        String[] tmp = new String[newSize];
        // 先遍历将原来的字符串数组数据添加到临时字符串数组
        for (int i = 0; i < size; i++) {
            tmp[i] = arr[i];
        }
        // 在末尾添加上需要追加的数据
        for (int i = size; i < newSize; i++) {
            tmp[i] = str[i - size];
        }
        return tmp; // 返回拼接完成的字符串数组
    }

    public static List<String> importCsv(File file){
        List<String> data = new ArrayList<String>();
        BufferedReader br = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            byte[] head = new byte[3];
            inputStream.read(head);
            InputStreamReader isr = null;
            if(head[0]==-17 && head[1]==-69 && head[2] ==-65){
                 isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            }else {
                isr = new InputStreamReader(new FileInputStream(file), "GBK");
            }
            br = new BufferedReader(isr);
            String line = "";
            while((line = br.readLine()) != null){
                data.add(line);
            }
        } catch (Exception e) {
            System.out.println("CSVUtils.importCsv error:"+e);
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("CSVUtils.importCsv close BufferedReader error:"+e);
            }
        }

        return data;
    }
}
