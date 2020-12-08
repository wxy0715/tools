package com.wxy.service;

import com.wxy.entity.SysUser;
import com.wxy.vo.req.LoginReqVO;
import com.wxy.vo.req.RegisterReqVO;
import com.wxy.vo.resp.LoginRespVO;

/**
 * @ClassName: UserService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface UserService {

    LoginRespVO login(LoginReqVO vo);

    SysUser getUserInfo(String id);

    String register(RegisterReqVO vo);

    String getCode(String phone);
}
