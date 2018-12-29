package org.flylib.mall.shop.entity;

import java.io.Serializable;
import java.util.Date;

public class MomentLike implements Serializable {
    private static final long serialVersionUID = -5598294670937626448L;
    private long id;
    /**
     * momentId作为分表依据
     */
    private long momentId;
    /**
     * 点赞的userId
     */
    private long likeUserId;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
        this.momentId = momentId;
    }

    public long getLikeUserId() {
        return likeUserId;
    }

    public void setLikeUserId(long likeUserId) {
        this.likeUserId = likeUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
