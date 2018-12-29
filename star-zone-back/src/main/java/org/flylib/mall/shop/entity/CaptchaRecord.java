package org.flylib.mall.shop.entity;

import java.io.Serializable;
import java.util.Date;

public class CaptchaRecord implements Serializable {
    private static final long serialVersionUID = 6503351809342615774L;
    private long id;
    private String mobile;
    private String captcha;
    private Date createTime;
    private Date expireTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "CaptchaRecord{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", captcha='" + captcha + '\'' +
                ", createTime=" + createTime +
                ", expireTime=" + expireTime +
                '}';
    }
}
