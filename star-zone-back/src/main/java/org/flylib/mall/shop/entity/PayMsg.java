package org.flylib.mall.shop.entity;

import java.io.Serializable;
import java.util.Date;

public class PayMsg implements Serializable {
    private long msgId;
    private String title;
    private String text;
    private long createTime;
    private int payType;
    private long amount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PayMsg{" +
                "msgId=" + msgId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
