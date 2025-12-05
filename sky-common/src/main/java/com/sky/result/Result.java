package com.sky.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 后端统一返回结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "后端统一返回结果")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "状态码，0表示失败，1表示成功")
    private Integer code;

    @Schema(description = "消息")
    private String msg;

    @Schema(description = "数据")
    private T data;

    public static Result<Void> success() {
        return Result.success("操作成功");
    }

    public static <T> Result<T> success(T data) {
        return Result.success("操作成功", data);
    }

    public static Result<Void> success(String msg) {
        return Result.success(msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(1, msg, data);
    }

    public static Result<Void> error() {
        return Result.error("操作失败");
    }

    public static Result<Void> error(String msg) {
        return Result.error(msg, null);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(0, msg, data);
    }
}
