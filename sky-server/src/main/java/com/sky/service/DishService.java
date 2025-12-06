package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * @author ming
 * @description 针对表【dish(菜品)】的数据库操作Service
 * @createDate 2025-12-06 13:46:05
 */
public interface DishService extends IService<Dish> {

    void save(DishDTO dishDTO);

    PageResult<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    DishVO getByIdWithFlavor(Long id);

    List<Dish> listByCategoryId(Long categoryId);

    void updateStatus(Integer status, Long id);

    void updateByDTO(DishDTO dishDTO);

    void deleteBatch(List<Long> ids);
}
