package com.shopping.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：分页工具类
 *
 * @author kuon ->[376079319@qq.com]
 * @version 1.0
 * 2018/7/5 8:56
 */
public class EUDataGridResult implements Serializable {
    private long total;
    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
