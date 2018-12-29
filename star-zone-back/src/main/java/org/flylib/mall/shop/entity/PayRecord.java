package org.flylib.mall.shop.entity;

import java.util.Date;

public class PayRecord {
    private long orderId;
    /**
     * 支付平台的商户订单号
     */
    private String outTradeNo;
    /**
     * 支付类型，0：空, 1: 微信支付, 2: 支付宝
     */
    private String payType;
    /**
     * 状态  0：未支付；1：已支付；2：已退款；3其它
     */
    private int payStatus;
    /**
     * 付款时间
     */
    private Date payTime;
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

}
