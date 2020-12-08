package com.wxy.controller;

import com.wxy.service.CartService;
import com.wxy.service.RedisService;
import com.wxy.vo.req.AddCartReqVO;
import com.wxy.vo.resp.CartRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: CartController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "购物车模块",description = "购物车模块相关接口")
public class CartController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private CartService cartService;
    @PostMapping("/cart")
    @ApiOperation(value = "加入购物车")
    public String addCart(@RequestBody AddCartReqVO vo, HttpServletRequest request){
        String token=request.getHeader("token");
        String userId= (String) redisService.get(token);
        return cartService.addCart(vo,userId);
    }

    @GetMapping("/cart")
    @ApiOperation(value = "获取用户购物车数据")
    public List<CartRespVO> showCart(HttpServletRequest request){
        String token=request.getHeader("token");
        String userId= (String) redisService.get(token);
        return cartService.showCart(userId);
    }
}
