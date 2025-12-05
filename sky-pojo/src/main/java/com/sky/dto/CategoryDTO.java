package com.sky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "分类DTO")
public class CategoryDTO implements Serializable {

    @Schema(description = "主键值")
    private Long id;

    @Schema(description = "类型 1 菜品分类 2 套餐分类", required = true)
    private Integer type;

    @Schema(description = "分类名称", required = true)
    private String name;

    @Schema(description = "排序", required = true)
    private Integer sort;

}
