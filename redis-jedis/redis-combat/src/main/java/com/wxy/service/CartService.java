package com.wxy.service;

import com.wxy.vo.req.AddCartReqVO;
import com.wxy.vo.resp.CartRespVO;

import java.util.List;

/**
 * @ClassName: CartService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface CartService {
    String addCart(AddCartReqVO vo, String userId);
    List<CartRespVO> showCart(String userId);
}
