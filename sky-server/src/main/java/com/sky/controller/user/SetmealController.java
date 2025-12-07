package com.sky.controller.user;

import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Tag(name = "C端-套餐浏览接口")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @GetMapping("/list")
    @Operation(summary = "根据分类ID查询套餐")
    public Result<List<Setmeal>> list(@Parameter(description = "分类ID", required = true) Long categoryId) {

        List<Setmeal> list = setmealService.listByCategoryId(categoryId);
        return Result.success(list);
    }

    @GetMapping("/dish/{id}")
    @Operation(summary = "根据套餐ID查询包含的菜品列表")
    public Result<List<DishItemVO>> dishList(@Parameter(description = "套餐ID", required = true) @PathVariable("id") Long id) {
        List<DishItemVO> list = setmealService.getDishItemById(id);
        return Result.success(list);
    }
}
