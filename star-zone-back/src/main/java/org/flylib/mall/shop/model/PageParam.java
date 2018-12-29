package org.flylib.mall.shop.model;

public class PageParam {
    /**
     * 第几页（从0开始）
     */
    private int page;
    /**
     * 每页多少条记录
     */
    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
