package com.sky.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.constant.RedisConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 套餐业务实现
 */
@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = RedisConstant.SETMEAL_CATEGORY)
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    private final SetmealDishMapper setmealDishMapper;

    @Override
    @Cacheable(key = "#categoryId")
    public List<Setmeal> listByCategoryId(Long categoryId) {
        return list(Wrappers.<Setmeal>lambdaQuery()
                .eq(Setmeal::getCategoryId, categoryId)
                .eq(Setmeal::getStatus, StatusConstant.ENABLE));
    }

    @Override
    public List<DishItemVO> getDishItemById(Long id) {
        // 多表查询setmeal_dish和dish表
        return baseMapper.getDishItemBySetmealId(id);
    }

    @Override
    public PageResult<SetmealVO> pageQuery(SetmealPageQueryDTO queryDTO) {
        Page<SetmealVO> page = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());
        Page<SetmealVO> result = baseMapper.pageQuery(page, queryDTO);
        return new PageResult<>(result.getTotal(), result.getRecords());
    }

    @Override
    @CacheEvict(key = "#setmealDTO.categoryId")
    public void saveByDTO(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        save(setmeal);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(dish -> dish.setSetmealId(setmeal.getId()));
        setmealDishMapper.insert(setmealDishes);
    }

    @Override
    public SetmealVO getByIdWithDishes(Long id) {
        Setmeal setmeal = getById(id);
        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal, setmealVO);

        List<SetmealDish> setmealDishes = setmealDishMapper.selectList(Wrappers.<SetmealDish>lambdaQuery()
                .eq(SetmealDish::getSetmealId, id));
        setmealVO.setSetmealDishes(setmealDishes);
        return setmealVO;
    }

    @Override
    @Transactional
    @CacheEvict(key = "#setmealDTO.categoryId")
    public void updateByDTO(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        updateById(setmeal);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(dish -> dish.setSetmealId(setmeal.getId()));
        setmealDishMapper.delete(Wrappers.<SetmealDish>lambdaQuery()
                .eq(SetmealDish::getSetmealId, setmeal.getId()));
        setmealDishMapper.insert(setmealDishes);
    }

    @Override
    @CacheEvict(key = "#root.target.getById(#id).categoryId")
    public void updateStatus(Integer status, Long id) {
        Setmeal setmeal = Setmeal.builder()
                .id(id)
                .status(status)
                .build();
        updateById(setmeal);
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public void deleteBatch(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new DeletionNotAllowedException("请选择要删除的套餐");
        }
        removeBatchByIds(ids);
        setmealDishMapper.delete(Wrappers.<SetmealDish>lambdaQuery()
                .in(SetmealDish::getSetmealId, ids));
    }
}
