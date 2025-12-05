package com.sky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "员工新增或修改时传递的数据模型")
public class EmployeeDTO implements Serializable {

    @Schema(description = "主键值")
    private Long id;

    @Schema(description = "用户名", required = true)
    private String username;

    @Schema(description = "姓名", required = true)
    private String name;

    @Schema(description = "手机号", required = true)
    private String phone;

    @Schema(description = "性别", required = true)
    private String sex;

    @Schema(description = "身份证号", required = true)
    private String idNumber;

}
