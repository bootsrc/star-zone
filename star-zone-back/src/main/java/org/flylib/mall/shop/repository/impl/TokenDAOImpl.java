package org.flylib.mall.shop.repository.impl;

import org.flylib.mall.shop.entity.User;
import org.flylib.passport.dao.TokenDAO;
import org.flylib.passport.entity.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TokenDAOImpl implements TokenDAO {
    private static final Logger log = LoggerFactory.getLogger(TokenDAOImpl.class);
    private static final String TABLE_NAME = "fly_token";
    private static final String COLUMNS = "user_id, token, create_time, update_time, expire_time";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String getToken(long userId) {
        final String sql = "SELECT token FROM " + TABLE_NAME + " WHERE user_id=:userId LIMIT 1";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        String token = null;
        try {
            token = namedParameterJdbcTemplate.queryForObject(sql, param, String.class);
        } catch (EmptyResultDataAccessException e) {
            token = null;
        }

        return token;
    }

    @Override
    public int add(Token token) {
        final String sql = "INSERT INTO " + TABLE_NAME + " (" + COLUMNS  + ") VALUES "
                + "( :userId, :token, :createTime, :updateTime, :expireTime )";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", token.getUserId());
        param.put("token", token.getToken());
        param.put("createTime", token.getCreateTime());
        param.put("updateTime", token.getUpdateTime());
        param.put("expireTime", token.getExpireTime());
        int updatedCount = namedParameterJdbcTemplate.update(sql, param);
        return updatedCount;
    }

    @Override
    public int updateToken(long userId, String token) {
        final String sql = "UPDATE " + TABLE_NAME + " SET token=:token, update_time=:updateTime " +
                "  WHERE user_id=:userId";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("token", token);
        param.put("updateTime", new Timestamp(new Date().getTime()));
        int updatedCount = namedParameterJdbcTemplate.update(sql, param);
        return updatedCount;
    }
}
