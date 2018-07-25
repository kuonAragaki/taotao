package com.shopping.pojo;

import java.util.Date;

public class TbCouponReceive {
    private Long id;

    private Long userId;

    private Long couponId;

    private String couponMoney;

    private Date createTime;

    private String fullMoney;

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

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(String couponMoney) {
        this.couponMoney = couponMoney == null ? null : couponMoney.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(String fullMoney) {
        this.fullMoney = fullMoney == null ? null : fullMoney.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}