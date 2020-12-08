package com.longersec.passwordsafe.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wxy
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pet_user")
public class PetUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("微信")
    private String wechat;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("照片url")
    private String photoUrl;

    @ApiModelProperty("头像地址")
    private Integer photoId;

    @ApiModelProperty("0禁用,1启用")
    private Integer statu;

    @ApiModelProperty("创建者id")
    private Integer creatId;

    @ApiModelProperty(value = "0未删除,1已删除")
    private Integer deleted;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
