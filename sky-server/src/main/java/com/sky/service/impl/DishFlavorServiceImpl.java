package com.sky.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.service.DishFlavorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ming
 * @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
 * @createDate 2025-12-06 15:56:22
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
        implements DishFlavorService {


    @Override
    @Transactional
    public void saveFlavors(Long dishId, List<DishFlavor> flavors) {
        if (flavors != null && !flavors.isEmpty()) {
            flavors.forEach(flavor -> flavor.setDishId(dishId));
            saveBatch(flavors);
        }
    }
}




