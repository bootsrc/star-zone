package org.flylib.mall.shop.entity;

import java.io.Serializable;

public class UserScore implements Serializable{
    private static final long serialVersionUID = 5446078356301835480L;
    private long userId;
    private int checkInCount;
    private int score;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getCheckInCount() {
        return checkInCount;
    }

    public void setCheckInCount(int checkInCount) {
        this.checkInCount = checkInCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
