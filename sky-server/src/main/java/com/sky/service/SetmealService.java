package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

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

    PageResult<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    void saveByDTO(SetmealDTO setmealDTO);

    SetmealVO getByIdWithDishes(Long id);

    void updateByDTO(SetmealDTO setmealDTO);

    void updateStatus(Integer status, Long id);

    void deleteBatch(List<Long> ids);
}
