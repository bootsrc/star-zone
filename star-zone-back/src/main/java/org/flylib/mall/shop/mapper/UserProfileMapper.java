package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.UserProfile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfileMapper implements RowMapper<UserProfile> {
    @Override
    public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserProfile userProfile = new UserProfile();
//        user_id, mobile, sex, star_sign, age

        userProfile.setUserId(rs.getLong("user_id"));
        userProfile.setMobile(rs.getString("mobile"));
        userProfile.setSex(rs.getInt("sex"));
        userProfile.setStarSign(rs.getInt("star_sign"));
        userProfile.setAge(rs.getInt("age"));
        userProfile.setNickname(rs.getString("nickname"));
        userProfile.setHeadImg(rs.getString("head_img"));
        userProfile.setHeadVersion(rs.getLong("head_version"));
        return userProfile;
    }
}
