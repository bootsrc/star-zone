package org.flylib.passport.entity;

import java.util.Date;

/**
* @author Frank Liu(liushaomingdev@163.com)
* @version 创建时间：2018年6月1日 上午9:58:06
* 类说明 
*/
public class Token {
    private long userId;
	private String token;
	private Date createTime;
	private Date updateTime;
    /**
     * TODO token过期时间，目前token的expireTime仅仅是预留字段，并没有在验证时对过期时间进行拦截
     */
	private Date expireTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
