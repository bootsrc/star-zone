package org.flylib.mall.shop.repository.impl;

import org.flylib.mall.shop.entity.Moment;
import org.flylib.mall.shop.entity.UserProfile;
import org.flylib.mall.shop.mapper.MomentMapper;
import org.flylib.mall.shop.mapper.UserProfileMapper;
import org.flylib.mall.shop.model.PageParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserProfileRepository {
    private static final Logger log = LoggerFactory.getLogger(UserProfileRepository.class);
    private static final String COLUMNS = " user_id, mobile, sex, star_sign, age, nickname" +
            ", head_img, head_version ";
    private static final String TABLE_NAME = "user_profile";


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(UserProfile userProfile) {
        String sql = "INSERT INTO " + TABLE_NAME + " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :userId, :mobile, :sex, :starSign, :age, :nickname, :headImg " +
                ", :headVersion "
                + " )";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("userId", userProfile.getUserId());
        parameter.put("mobile", userProfile.getMobile());
        parameter.put("sex", userProfile.getSex());
        parameter.put("starSign", userProfile.getStarSign());
        parameter.put("age", userProfile.getAge());
        parameter.put("nickname", userProfile.getNickname());
        parameter.put("headImg", userProfile.getHeadImg());
        parameter.put("headVersion", userProfile.getHeadVersion());
        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public int update(UserProfile userProfile) {
        String sqlFormat = "UPDATE " + TABLE_NAME
                + " SET mobile=:mobile, sex=:sex, star_sign=:starSign, age=:age" +
                ", head_version=:headVersion, nickname=:nickname %s "
                + " WHERE user_id=:userId";

        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("userId", userProfile.getUserId());
        parameter.put("mobile", userProfile.getMobile());
        parameter.put("sex", userProfile.getSex());
        parameter.put("starSign", userProfile.getStarSign());
        parameter.put("age", userProfile.getAge());
        parameter.put("nickname", userProfile.getNickname());
        parameter.put("headVersion", userProfile.getHeadVersion());
        String sql;
        if (isHeadImgValid(userProfile.getHeadImg())) {
            sql = String.format(sqlFormat, ", head_img=:headImg");
            parameter.put("headImg", userProfile.getHeadImg());
        } else {
            sql = String.format(sqlFormat, "");
        }

        log.info("SQLparameter={}", parameter);
        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    private boolean isHeadImgValid(String headImg) {
        if (!StringUtils.isEmpty(headImg)) {
            if (headImg.startsWith("/star-sign/")
                    || headImg.startsWith("/star-sign-file/mobile/default-img/")) {
                return true;
            }
        }
        return false;
    }

    public UserProfile findById(long userId) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE user_id=?";
        UserProfile userProfile = null;
        try {
            userProfile = jdbcTemplate.queryForObject(sql, new Object[]{userId}, new int[]{Types.BIGINT}
                    , new UserProfileMapper());
        } catch (EmptyResultDataAccessException e) {
            userProfile = null;
        }
        return userProfile;
    }

    public List<UserProfile> byPageForMobile(PageParam pageParam) {
        int start = pageParam.getLimit() * pageParam.getPage();
        final String sql = "SELECT * FROM " + TABLE_NAME
                + " ORDER BY user_id "
                + " LIMIT " + start + ", " + pageParam.getLimit();

        List<UserProfile> userProfileList = jdbcTemplate.query(sql, new UserProfileMapper());
        return userProfileList;
    }
}
