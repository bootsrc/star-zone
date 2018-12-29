package org.flylib.mall.shop.vo.mobile;

/**
 * Created by liushaoming on 2017/3/28.
 */

public class LikeInfo {
    private long id;
    private long momentId;
    private UserInfo userInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
        this.momentId = momentId;
    }

}
