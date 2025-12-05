package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 */
@RestController
@RequestMapping("/admin/category")
@Tag(name = "分类管理")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @Operation(summary = "新增分类")
    public Result<Void> save(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @Operation(summary = "分类分页查询")
    public Result<PageResult<Category>> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询：{}", categoryPageQueryDTO);
        PageResult<Category> pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @Operation(summary = "删除分类")
    public Result<Void> deleteById(@Parameter(description = "分类ID", required = true) Long id) {
        log.info("删除分类：id={}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    @Operation(summary = "修改分类")
    public Result<Void> update(@RequestBody CategoryDTO categoryDTO) {
        log.info("修改分类：{}", categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "启用禁用分类")
    public Result<Void> startOrStop(@Parameter(description = "状态", required = true) @PathVariable("status") Integer status, @Parameter(description = "分类ID", required = true) Long id) {
        log.info("启用禁用分类：status={}, id={}", status, id);
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    @GetMapping("/list")
    @Operation(summary = "根据类型查询分类")
    public Result<List<Category>> list(@Parameter(description = "分类类型", required = true) Integer type) {
        log.info("根据类型查询分类：type={}", type);
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
