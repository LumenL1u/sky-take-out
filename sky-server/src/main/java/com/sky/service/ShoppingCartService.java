package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

/**
* @author ming
* @description 针对表【shopping_cart(购物车)】的数据库操作Service
* @createDate 2025-12-09 01:57:46
*/
public interface ShoppingCartService extends IService<ShoppingCart> {

    void add(ShoppingCartDTO shoppingCartDTO);

    void sub(ShoppingCartDTO shoppingCartDTO);

    void clean();

    ShoppingCart getByDTO(ShoppingCartDTO shoppingCartDTO);
}
