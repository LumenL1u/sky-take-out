package com.sky.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.constant.StatusConstant;
import com.sky.entity.Setmeal;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 套餐业务实现
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Override
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
}
