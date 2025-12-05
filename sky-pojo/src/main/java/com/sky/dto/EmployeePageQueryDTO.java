package com.sky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "员工分页查询时传递的数据模型")
public class EmployeePageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "员工姓名")
    private String name;

    @Schema(description = "页码", required = true)
    private int page;

    @Schema(description = "每页显示记录数", required = true)
    private int pageSize;
}
