package com.manager.common.core.page;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author marvin
 */
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    /**
     * 每页条数
     */
    private int size;

    /**
     * 当前页数条数
     */
    private int page;

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }

    public TableDataInfo(long total, List<?> rows, int size, int page) {
        this.total = total;
        this.rows = rows;
        this.size = size;
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
