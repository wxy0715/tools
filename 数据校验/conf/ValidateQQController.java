  0/**
 * Project Name:dt48_springMVC4
 * File Name:ValidateQQ.java
 * Package Name:cn.java.controller.front
 * Date:下午2:45:58
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.java.controller.front;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.java.entity.QQ;
import cn.java.utils.Validator;

/**
 * Description: <br/>
 * Date: 下午2:45:58 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
@Controller
public class ValidateQQController {
    @RequestMapping("register")
    public void register(QQ qqInfo) {
        String nick = qqInfo.getNick();
        boolean flag1 = nick.matches("[\\u4e00-\\u9fa5]{4,10}");

        String password = qqInfo.getPassword();
        boolean flag2 = password.matches("\\w{3,6}");

        String phone = qqInfo.getPhone();
        boolean flag3 = phone.matches("1[3578]\\d{9}");

        String email = qqInfo.getEmail();
        // 123@qq.com 123abc@163.com abc_123@126.com.cn
        boolean flag4 = email.matches("\\w{3,12}@([a-zA-Z]{2,5}|\\d{2,5})(\\.[a-zA-Z]{2,5}){1,3}");

        Integer age = qqInfo.getAge();
        boolean flag5 = false;
        if (age >= 1 && age <= 150) {
            flag5 = true;
        }

        if (flag1 && flag2 && flag3 && flag4 && flag5) {
            System.out.println("数据正确");
        } else {
            System.out.println("对不起，您输入的数据有误");
        }

    }

    @RequestMapping("register2")
    public String register2(@Valid QQ qqInfo, BindingResult errorResult, Model model) {
        // 调用校验数据的工具类
        Map<String, Object> resultMap = Validator.fieldValidate(errorResult);
        if (resultMap == null) {// 数据正确
            return "front/regiter_success.jsp";
        } else {// 数据错误
            model.addAttribute("errorMessage", resultMap);
            return "front/register.jsp";
        }
    }
}
