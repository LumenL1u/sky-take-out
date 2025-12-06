package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.DishFlavor;

import java.util.List;

/**
 * @author ming
 * @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service
 * @createDate 2025-12-06 15:56:22
 */
public interface DishFlavorService extends IService<DishFlavor> {

    void saveFlavors(Long dishId, List<DishFlavor> flavors);
}
