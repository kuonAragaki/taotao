package com.shopping.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TbCouponLogs {
    private Long id;

    private Long userId;

    private Long couponReceiveId;

    private String orderId;

    private BigDecimal orderOriginalAmount;

    private BigDecimal couponAmount;

    private BigDecimal orderFinalAmount;

    private Date createTime;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCouponReceiveId() {
        return couponReceiveId;
    }

    public void setCouponReceiveId(Long couponReceiveId) {
        this.couponReceiveId = couponReceiveId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public BigDecimal getOrderOriginalAmount() {
        return orderOriginalAmount;
    }

    public void setOrderOriginalAmount(BigDecimal orderOriginalAmount) {
        this.orderOriginalAmount = orderOriginalAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getOrderFinalAmount() {
        return orderFinalAmount;
    }

    public void setOrderFinalAmount(BigDecimal orderFinalAmount) {
        this.orderFinalAmount = orderFinalAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}