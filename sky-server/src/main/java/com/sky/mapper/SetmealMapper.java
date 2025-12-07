package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {

    /**
     * 根据套餐id查询包含的菜品
     *
     * @param setmealId 套餐id
     * @return 菜品列表
     */
    @Select("SELECT sd.name, sd.copies, d.image, d.description " +
            "FROM setmeal_dish sd " +
            "LEFT JOIN dish d ON sd.dish_id = d.id " +
            "WHERE sd.setmeal_id = #{setmealId}")
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);
}
