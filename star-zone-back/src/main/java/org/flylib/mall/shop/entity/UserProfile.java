package org.flylib.mall.shop.entity;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 235854040226282703L;
    private long userId;
    private String mobile;
    /**
     * 取值是例如 {@link org.flylib.mall.common.constant.UserProfileConstant#SEX_MALE }
     */
    private int sex;

    /**
     * 星座值，0-山羊座，11是最后一个星座
     * 默认山羊座=0
      */
    private int starSign;
    private int age;
//    private int city;
    private String nickname;
    private String headImg;
    private long headVersion;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStarSign() {
        return starSign;
    }

    public void setStarSign(int starSign) {
        this.starSign = starSign;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public long getHeadVersion() {
        return headVersion;
    }

    public void setHeadVersion(long headVersion) {
        this.headVersion = headVersion;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", mobile='" + mobile + '\'' +
                ", sex=" + sex +
                ", starSign=" + starSign +
                ", age=" + age +
                ", nickname='" + nickname + '\'' +
                ", headImg='" + headImg + '\'' +
                ", headVersion=" + headVersion +
                '}';
    }
}
