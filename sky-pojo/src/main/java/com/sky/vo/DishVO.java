package com.sky.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sky.entity.DishFlavor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "菜品VO")
public class DishVO implements Serializable {

    @Schema(description = "菜品ID")
    private Long id;

    @Schema(description = "菜品名称")
    private String name;

    @Schema(description = "菜品分类ID")
    private Long categoryId;

    @Schema(description = "菜品价格")
    private BigDecimal price;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "描述信息")
    private String description;

    @Schema(description = "0 停售 1 起售")
    private Integer status;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "菜品关联的口味")
    private List<DishFlavor> flavors = new ArrayList<>();

    // private Integer copies;
}
