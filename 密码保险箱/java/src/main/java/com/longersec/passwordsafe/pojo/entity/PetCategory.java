package com.longersec.passwordsafe.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PetCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**分类唯一ID*/
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**分类名称*/
    private String name;

    /**分类父ID*/
    private Integer pid;

    /**分类层级 0: 顶级 1:二级 2:三级*/
    private Integer level;

    /**是否删除 1为删除*/
    private Integer deleted;

    private String icon;

    private String src;
}
