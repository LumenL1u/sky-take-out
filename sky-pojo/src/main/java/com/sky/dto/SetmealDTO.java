package com.sky.dto;

import com.sky.entity.SetmealDish;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "套餐DTO")
public class SetmealDTO implements Serializable {

    @Schema(description = "主键值")
    private Long id;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "套餐名称")
    private String name;

    @Schema(description = "套餐价格")
    private BigDecimal price;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "描述信息")
    private String description;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "套餐菜品列表")
    private List<SetmealDish> setmealDishes = new ArrayList<>();

}
