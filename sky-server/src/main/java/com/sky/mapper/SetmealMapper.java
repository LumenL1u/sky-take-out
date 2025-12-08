package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {

    /**
     * 根据套餐id查询包含的菜品
     *
     * @param setmealId 套餐id
     * @return 菜品列表
     */
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);

    Page<SetmealVO> pageQuery(@Param("page") Page<SetmealVO> page,
                              @Param("queryDTO") SetmealPageQueryDTO queryDTO);
}
