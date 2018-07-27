package com.shopping.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbSeckill implements Serializable {
    private Long itemId;

    private String title;

    private Integer num;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    private Long originPrice;

    private Long seckillPrice;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Long originPrice) {
        this.originPrice = originPrice;
    }

    public Long getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Long seckillPrice) {
        this.seckillPrice = seckillPrice;
    }
}