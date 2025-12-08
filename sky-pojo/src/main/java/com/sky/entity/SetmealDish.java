package com.sky.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 套餐菜品关系
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "套餐菜品关系")
public class SetmealDish implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键值")
    private Long id;

    @Schema(description = "套餐id")
    private Long setmealId;

    @Schema(description = "菜品id")
    private Long dishId;

    @Schema(description = "菜品名称 （冗余字段）")
    private String name;

    @Schema(description = "菜品原价")
    private BigDecimal price;

    @Schema(description = "份数")
    private Integer copies;
}
