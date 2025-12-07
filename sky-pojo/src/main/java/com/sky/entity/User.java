package com.sky.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "C端用户")
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键值")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "微信用户唯一标识")
    private String openid;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "性别 0 女 1 男")
    private String sex;

    @Schema(description = "身份证号")
    private String idNumber;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "注册时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
