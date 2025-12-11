package com.sky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "订单")
public class Orders implements Serializable {

    /**
     * 订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消
     */
    public static final Integer PENDING_PAYMENT = 1;
    public static final Integer TO_BE_CONFIRMED = 2;
    public static final Integer CONFIRMED = 3;
    public static final Integer DELIVERY_IN_PROGRESS = 4;
    public static final Integer COMPLETED = 5;
    public static final Integer CANCELLED = 6;

    /**
     * 支付状态 0未支付 1已支付 2退款
     */
    public static final Integer UN_PAID = 0;
    public static final Integer PAID = 1;
    public static final Integer REFUND = 2;

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键值")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "订单号")
    private String number;

    @Schema(description = "订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款")
    private Integer status;

    @Schema(description = "下单用户id")
    private Long userId;

    @Schema(description = "地址id")
    private Long addressBookId;

    @Schema(description = "下单时间")
    private LocalDateTime orderTime;

    @Schema(description = "结账时间")
    private LocalDateTime checkoutTime;

    @Schema(description = "支付方式 1微信，2支付宝")
    private Integer payMethod;

    @Schema(description = "支付状态 0未支付 1已支付 2退款")
    private Integer payStatus;

    @Schema(description = "实收金额")
    private BigDecimal amount;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "收货人")
    private String consignee;

    @Schema(description = "订单取消原因")
    private String cancelReason;

    @Schema(description = "订单拒绝原因")
    private String rejectionReason;

    @Schema(description = "订单取消时间")
    private LocalDateTime cancelTime;

    @Schema(description = "预计送达时间")
    private LocalDateTime estimatedDeliveryTime;

    @Schema(description = "配送状态  1立即送出  0选择具体时间")
    private Integer deliveryStatus;

    @Schema(description = "送达时间")
    private LocalDateTime deliveryTime;

    @Schema(description = "打包费")
    private int packAmount;

    @Schema(description = "餐具数量")
    private int tablewareNumber;

    @Schema(description = "餐具数量状态  1按餐量提供  0选择具体数量")
    private Integer tablewareStatus;
}
