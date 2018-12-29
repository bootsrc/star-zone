package org.flylib.mall.shop.entity;

import org.flylib.mall.shop.model.CartItem;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 7957638976331551705L;
    private long id;
    private long userId;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 收货人电话
     */
    private String consigneeMoblie;
    /**
     * 备注
     */
    private String remark;

    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区县
     */
    private String district;
    /**
     * 运单号
     */
    private String expressId;
    /**
     * 发货时间
     */
    private Date deliverTime;
    /**
     * 确认收货时间
     */
    private Date confirmTime;
    /**
     * 配送方式  0 快递  1自提
     */
    private int deliveryWay;
    /**
     * 购物车里所有的商品信息(json string)
     */
    private String cartItemList;
    /**
     * 使用的所有优惠券(json string)
     */
    private String discountList;
    /**
     * 商品总价格(不含运费)
     */
    private long amount;
    /**
     * 邮费
     */
    private long postage;
    /**
     * shouldPay = 商品总价格 + postage - discountList总金额
     */
    private long shouldPay;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeMoblie() {
        return consigneeMoblie;
    }

    public void setConsigneeMoblie(String consigneeMoblie) {
        this.consigneeMoblie = consigneeMoblie;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public int getDeliveryWay() {
        return deliveryWay;
    }

    public void setDeliveryWay(int deliveryWay) {
        this.deliveryWay = deliveryWay;
    }

    public String getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(String cartItemList) {
        this.cartItemList = cartItemList;
    }

    public String getDiscountList() {
        return discountList;
    }

    public void setDiscountList(String discountList) {
        this.discountList = discountList;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getPostage() {
        return postage;
    }

    public void setPostage(long postage) {
        this.postage = postage;
    }

    public long getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(long shouldPay) {
        this.shouldPay = shouldPay;
    }
}
