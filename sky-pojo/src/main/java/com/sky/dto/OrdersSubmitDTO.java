package com.sky.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "订单提交DTO")
public class OrdersSubmitDTO implements Serializable {

    @Schema(description = "地址簿ID")
    private Long addressBookId;

    @Schema(description = "付款方式")
    private Integer payMethod;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "预计送达时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;

    @Schema(description = "配送状态 1立即送出 0选择具体时间")
    private Integer deliveryStatus;

    @Schema(description = "餐具数量")
    private Integer tablewareNumber;

    @Schema(description = "餐具数量状态 1按餐量提供 0选择具体数量")
    private Integer tablewareStatus;

    @Schema(description = "打包费")
    private Integer packAmount;

    @Schema(description = "总金额")
    private BigDecimal amount;
}
