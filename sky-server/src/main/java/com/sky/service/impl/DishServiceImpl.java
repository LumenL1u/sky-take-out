package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.constant.MessageConstant;
import com.sky.constant.RedisConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishFlavorService;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ming
 * @description 针对表【dish(菜品)】的数据库操作Service实现
 * @createDate 2025-12-06 13:46:05
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = RedisConstant.DISH_CATEGORY)
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
        implements DishService {

    private final DishFlavorService dishFlavorService;
    private final SetmealDishMapper setmealDishMapper;

    @Override
    @Transactional
    @CacheEvict(key = "#dishDTO.categoryId")
    public void save(DishDTO dishDTO) {
        Dish dish = Dish.builder()
                .name(dishDTO.getName())
                .categoryId(dishDTO.getCategoryId())
                .price(dishDTO.getPrice())
                .image(dishDTO.getImage())
                .description(dishDTO.getDescription())
                .status(dishDTO.getStatus())
                .build();
        save(dish);

        dishFlavorService.saveFlavors(dish.getId(), dishDTO.getFlavors());
    }

    @Override
    public PageResult<DishVO> pageQuery(DishPageQueryDTO queryDTO) {
        // 创建分页对象，泛型类型必须与 Mapper 方法的返回类型一致
        Page<DishVO> page = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());
        // 调用 Mapper 的分页查询方法
        Page<DishVO> result = baseMapper.pageQuery(page, queryDTO);
        // 封装成自定义的 PageResult 对象返回
        return new PageResult<>(result.getTotal(), result.getRecords());
    }

    @Override
    public DishVO getByIdWithFlavor(Long id) {
        Dish dish = getById(id);
        List<DishFlavor> flavors = dishFlavorService.list(new LambdaQueryWrapper<DishFlavor>()
                .eq(DishFlavor::getDishId, id));
        return DishVO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .categoryId(dish.getCategoryId())
                .price(dish.getPrice())
                .image(dish.getImage())
                .description(dish.getDescription())
                .status(dish.getStatus())
                .flavors(flavors)
                .build();
    }

    @Override
    public List<Dish> listByCategoryId(Long categoryId) {
        return list(new LambdaQueryWrapper<Dish>()
                .eq(Dish::getCategoryId, categoryId)
                .eq(Dish::getStatus, StatusConstant.ENABLE));
    }

    @Override
    @CacheEvict(key = "#root.target.getById(#id).categoryId")
    public void updateStatus(Integer status, Long id) {
        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        updateById(dish);
    }

    @Override
    @CacheEvict(key = "#dishDTO.categoryId")
    public void updateByDTO(DishDTO dishDTO) {
        Dish dish = Dish.builder()
                .id(dishDTO.getId())
                .name(dishDTO.getName())
                .categoryId(dishDTO.getCategoryId())
                .price(dishDTO.getPrice())
                .image(dishDTO.getImage())
                .description(dishDTO.getDescription())
                .status(dishDTO.getStatus())
                .build();
        // 更新菜品信息
        updateById(dish);

        // 删除原有的口味
        dishFlavorService.remove(new LambdaQueryWrapper<DishFlavor>().eq(DishFlavor::getDishId, dish.getId()));

        // 保存新的口味
        dishFlavorService.saveFlavors(dish.getId(), dishDTO.getFlavors());
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void deleteBatch(List<Long> ids) {
        // 起售的菜品不能删除
        for (Long id : ids) {
            Dish dish = getById(id);
            if (dish != null && dish.getStatus().equals(StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        // 和套餐关联的菜品不能删除
        Long count = setmealDishMapper.selectCount(new LambdaQueryWrapper<SetmealDish>().in(SetmealDish::getDishId, ids));
        if (count > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        // 删除菜品相关联的口味
        dishFlavorService.remove(new LambdaQueryWrapper<DishFlavor>().in(DishFlavor::getDishId, ids));

        // 删除菜品
        removeByIds(ids);
    }

    @Override
    @Cacheable(key = "#categoryId")
    public List<DishVO> listWithFlavor(Long categoryId) {
        // 从数据库中查询菜品信息
        List<Dish> dishList = list(new LambdaQueryWrapper<Dish>()
                .eq(Dish::getCategoryId, categoryId)
                .eq(Dish::getStatus, StatusConstant.ENABLE));
        List<DishVO> dishVOList = new ArrayList<>();
        for (Dish d : dishList) {
            List<DishFlavor> flavors = dishFlavorService.list(new LambdaQueryWrapper<DishFlavor>()
                    .eq(DishFlavor::getDishId, d.getId()));
            DishVO dishVO = DishVO.builder()
                    .id(d.getId())
                    .name(d.getName())
                    .categoryId(d.getCategoryId())
                    .price(d.getPrice())
                    .image(d.getImage())
                    .description(d.getDescription())
                    .status(d.getStatus())
                    .flavors(flavors)
                    .build();
            dishVOList.add(dishVO);
        }
        return dishVOList;
    }
}