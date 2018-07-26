package com.shopping.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbCouponLogs implements Serializable {
    private Long id;

    private Long userId;

    private Long couponReceiveId;

    private String orderId;

    private String orderOriginalAmount;

    private String couponAmount;

    private String orderFinalAmount;

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

    public String getOrderOriginalAmount() {
        return orderOriginalAmount;
    }

    public void setOrderOriginalAmount(String orderOriginalAmount) {
        this.orderOriginalAmount = orderOriginalAmount == null ? null : orderOriginalAmount.trim();
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount == null ? null : couponAmount.trim();
    }

    public String getOrderFinalAmount() {
        return orderFinalAmount;
    }

    public void setOrderFinalAmount(String orderFinalAmount) {
        this.orderFinalAmount = orderFinalAmount == null ? null : orderFinalAmount.trim();
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