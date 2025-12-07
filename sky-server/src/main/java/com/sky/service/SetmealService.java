package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    List<Setmeal> listByCategoryId(Long categoryId);

    /**
     * 根据id查询菜品选项
     *
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
