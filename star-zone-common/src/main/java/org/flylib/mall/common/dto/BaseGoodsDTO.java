package org.flylib.mall.common.dto;

import java.io.Serializable;

public class BaseGoodsDTO implements Serializable {
    private static final long serialVersionUID = 8783169436595730526L;
    private long id;
    /**
     * 商品分类ID
     */
    private int catalogId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 小图片地址
     */
    private String img;
    /**
     * 标签,多个标签用逗号隔开
     */
    private String label;
    /**
     * 现价,单位'分'
     */
    private int price;
    /**
     * 原价,单位'分'
     */
    private int originPrice;
    /**
     * 是否爆款
     */
    private boolean hot;
    /**
     * 商品状态。0：已下架，1：已上架, 2:缺货
     */
    private int status;
    /**
     * 排序值
     */
    private int sortValue;
    private int stock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(int originPrice) {
        this.originPrice = originPrice;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSortValue() {
        return sortValue;
    }

    public void setSortValue(int sortValue) {
        this.sortValue = sortValue;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
