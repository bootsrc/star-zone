package org.flylib.mall.shop.model;

import java.io.Serializable;

public class PageForm implements Serializable {
    private static final long serialVersionUID = 5330190860504932276L;
    private int pageNo = 0;
    private int pageSize = 10;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
