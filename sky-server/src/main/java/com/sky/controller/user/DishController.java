package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Tag(name = "C端-菜品浏览接口")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("/list")
    @Operation(summary = "根据分类ID查询菜品")
    public Result<List<DishVO>> list(@Parameter(description = "分类ID", required = true) Long categoryId) {

        List<DishVO> list = dishService.listWithFlavor(categoryId);

        return Result.success(list);
    }
}
