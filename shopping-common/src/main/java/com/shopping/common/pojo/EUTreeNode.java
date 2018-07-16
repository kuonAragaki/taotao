package com.shopping.common.pojo;

import java.io.Serializable;

/**
 * 说明：easyUI树形空间节点格式
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/5 14:36
 */
public class EUTreeNode implements Serializable {
    private long id;
    private String text;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
