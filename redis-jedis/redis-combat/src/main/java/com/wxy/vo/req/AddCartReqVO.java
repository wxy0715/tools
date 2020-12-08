package com.wxy.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: AddCartReqVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class AddCartReqVO {
    @ApiModelProperty(value = "商品skuId")
    private String skuId;
    @ApiModelProperty(value = "属性规格id拼接集合(以逗号隔开)")
    private String specificationIds;
    @ApiModelProperty(value = "商品数量")
    private Integer num;
}
