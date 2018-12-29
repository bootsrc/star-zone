package org.flylib.mall.shop.entity;

import java.io.Serializable;

public class AccountData implements Serializable {
    private long id;
    private long userId;
    private String miRegid;
    private long createTime;
    private long updateTime;

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

    public String getMiRegid() {
        return miRegid;
    }

    public void setMiRegid(String miRegid) {
        this.miRegid = miRegid;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
