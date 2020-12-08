package com.wxy.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: CartRespVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class CartRespVO {
    @ApiModelProperty("商品skuId")
    private String skuId;
    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "规格属性")
    private List<ValueItemRespVO> itemRespVOList;
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    @ApiModelProperty(value = "数量")
    private Integer num;
    @ApiModelProperty(value = "图标")
    private String icon;
}
