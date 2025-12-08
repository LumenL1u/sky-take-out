package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.exception.ShoppingCartBusinessException;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.DishService;
import com.sky.service.SetmealService;
import com.sky.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author ming
 * @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
 * @createDate 2025-12-09 01:57:46
 */
@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

    private final DishService dishService;
    private final SetmealService setmealService;

    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = getByDTO(shoppingCartDTO);
        if (shoppingCart != null) {
            shoppingCart.setNumber(shoppingCart.getNumber() + 1);
            updateById(shoppingCart);
            return;
        }
        shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());

        if (shoppingCartDTO.getDishId() != null) {
            Dish dish = dishService.getById(shoppingCartDTO.getDishId());
            if (dish == null) {
                throw new ShoppingCartBusinessException("菜品不存在");
            }
            shoppingCart.setName(dish.getName());
            shoppingCart.setAmount(dish.getPrice());
            shoppingCart.setImage(dish.getImage());
        } else if (shoppingCartDTO.getSetmealId() != null) {
            Setmeal setmeal = setmealService.getById(shoppingCartDTO.getSetmealId());
            shoppingCart.setName(setmeal.getName());
            shoppingCart.setAmount(setmeal.getPrice());
            shoppingCart.setImage(setmeal.getImage());
        }
        save(shoppingCart);
    }

    @Override
    public void sub(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = getByDTO(shoppingCartDTO);
        if (shoppingCart == null) {
            throw new ShoppingCartBusinessException("购物车中不存在该商品");
        }
        shoppingCart.setNumber(shoppingCart.getNumber() - 1);
        if (shoppingCart.getNumber() == 0) {
            removeById(shoppingCart.getId());
        } else {
            updateById(shoppingCart);
        }
    }

    @Override
    public void clean() {
        remove(Wrappers.<ShoppingCart>lambdaQuery()
                .eq(ShoppingCart::getUserId, BaseContext.getCurrentId()));
    }

    @Override
    public ShoppingCart getByDTO(ShoppingCartDTO shoppingCartDTO) {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = Wrappers.lambdaQuery(ShoppingCart.class)
                .select(ShoppingCart::getId, ShoppingCart::getNumber)
                .eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        if (shoppingCartDTO.getDishId() != null) {
            queryWrapper.eq(ShoppingCart::getDishId, shoppingCartDTO.getDishId())
                    .eq(shoppingCartDTO.getDishFlavor() != null, ShoppingCart::getDishFlavor, shoppingCartDTO.getDishFlavor());
        } else if (shoppingCartDTO.getSetmealId() != null) {
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCartDTO.getSetmealId());
        }
        return getOne(queryWrapper);
    }
}




