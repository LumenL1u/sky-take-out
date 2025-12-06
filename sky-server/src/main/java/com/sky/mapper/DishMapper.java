package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author ming
 * @description 针对表【dish(菜品)】的数据库操作Mapper
 * @createDate 2025-12-06 13:46:05
 * @Entity com.sky.entity.Dish
 */
public interface DishMapper extends BaseMapper<Dish> {

    /**
     * 分页查询菜品（多表关联）
     * <p>
     * 关键点：
     * 1. 第一个参数必须是 Page<T> 类型，MyBatis-Plus 会自动处理分页
     * 2. 返回值也是 Page<T>，会自动填充 total、records 等信息
     * 3. 其他参数建议用 @Param 注解（虽然单个参数可以省略）
     *
     * @param page     分页对象
     * @param queryDTO 查询条件
     * @return 分页结果
     */
    Page<DishVO> pageQuery(@Param("page") Page<DishVO> page,
                           @Param("queryDTO") DishPageQueryDTO queryDTO);
}




