package com.sky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "菜品分页查询DTO")
public class DishPageQueryDTO implements Serializable {

    @Schema(description = "页码", required = true)
    private Integer page;

    @Schema(description = "每页显示记录数", required = true)
    private Integer pageSize;

    @Schema(description = "菜品名称")
    private String name;

    @Schema(description = "菜品分类ID")
    private Long categoryId;

    @Schema(description = "状态 0表示禁用 1表示启用")
    private Integer status;

}
