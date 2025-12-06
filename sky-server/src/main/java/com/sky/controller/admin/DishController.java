package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Tag(name = "菜品管理")
@Slf4j
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping
    @Operation(summary = "新增菜品")
    public Result<Void> save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);
        dishService.save(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询菜品")
    public Result<PageResult<DishVO>> page(@ParameterObject DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询菜品：{}", dishPageQueryDTO);
        PageResult<DishVO> pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据id查询菜品")
    public Result<DishVO> getById(@Parameter(description = "菜品ID", required = true) @PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @GetMapping("/list")
    @Operation(summary = "根据分类ID查询菜品")
    public Result<List<Dish>> list(@Parameter(description = "菜品分类ID", required = true) Long categoryId) {
        log.info("根据分类ID查询菜品：categoryId={}", categoryId);
        List<Dish> list = dishService.listByCategoryId(categoryId);
        return Result.success(list);
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "菜品起售/停售")
    public Result<Void> updateStatus(@Parameter(description = "状态", required = true) @PathVariable("status") Integer status, @Parameter(description = "菜品ID", required = true) Long id) {
        log.info("菜品起售/停售：status={}, id={}", status, id);
        dishService.updateStatus(status, id);
        return Result.success();
    }

    @PutMapping
    @Operation(summary = "修改菜品")
    public Result<Void> update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品：{}", dishDTO);
        dishService.updateByDTO(dishDTO);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除菜品")
    public Result<Void> deleteByIds(@Parameter(description = "菜品ID列表", required = true) @RequestParam List<Long> ids) {
        log.info("批量删除菜品：ids={}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }
}
