package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Tag(name = "套餐管理")
@Slf4j
@RequiredArgsConstructor
public class SetmealController {

    private final SetmealService setmealService;

    @GetMapping("/page")
    @Operation(summary = "分页查询套餐")
    public Result<PageResult<SetmealVO>> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("分页查询套餐，参数：{}", setmealPageQueryDTO);
        PageResult<SetmealVO> pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    @Operation(summary = "新增套餐")
    public Result<Void> save(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐：{}", setmealDTO);
        setmealService.saveByDTO(setmealDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询套餐")
    public Result<SetmealVO> getById(@Parameter(description = "套餐ID", required = true) @PathVariable Long id) {
        log.info("根据ID查询套餐：id={}", id);
        SetmealVO setmealVO = setmealService.getByIdWithDishes(id);
        return Result.success(setmealVO);
    }

    @PutMapping
    @Operation(summary = "修改套餐")
    public Result<Void> update(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改套餐：{}", setmealDTO);
        setmealService.updateByDTO(setmealDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "套餐起售/停售")
    public Result<Void> updateStatus(@Parameter(description = "状态", required = true) @PathVariable("status") Integer status, @Parameter(description = "套餐ID", required = true) Long id) {
        log.info("套餐起售/停售：status={}, id={}", status, id);
        setmealService.updateStatus(status, id);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除套餐")
    public Result<Void> deleteByIds(@Parameter(description = "套餐ID列表", required = true) @RequestParam List<Long> ids) {
        log.info("批量删除套餐：ids={}", ids);
        setmealService.deleteBatch(ids);
        return Result.success();
    }
}
