package com.wxy.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: LoginRespVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class LoginRespVO {
    @ApiModelProperty(value = "用户认证凭证")
    private String token;

    @ApiModelProperty(value = "用户id")
    private String userId;
}
