package com.sky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜品口味
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "菜品口味")
public class DishFlavor implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键值")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "菜品ID")
    private Long dishId;

    @Schema(description = "口味名称")
    private String name;

    @Schema(description = "口味值")
    private String value;

}
