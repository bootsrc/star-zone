package org.flylib.mall.shop.repository.impl;

import org.flylib.mall.shop.entity.User;
import org.flylib.mall.shop.mapper.UserMapper;
import org.flylib.mall.shop.repository.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
    private static final String COLUMNS = " id, create_time, update_time, status, password, mobile ";
    private static final String TABLE_NAME = "fly_user";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(User user) {
        String sql = "INSERT INTO " + TABLE_NAME + " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :id, :createTime, :updateTime, :status, :password, "
                + ":mobile )";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", user.getId());
        param.put("createTime", new Timestamp(user.getCreateTime().getTime()));
        param.put("updateTime", new Timestamp(user.getUpdateTime().getTime()));
        param.put("status", user.getStatus());
        param.put("password", user.getPassword());
        param.put("mobile", user.getMobile());
        int updatedCount = namedParameterJdbcTemplate.update(sql, param);
        return updatedCount;
    }

    public User getUserByMobile(String mobile) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE mobile=? LIMIT 1";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{mobile}, new int[]{Types.VARCHAR}
                    , new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }
        return user;
    }

    public int updatePassword(long userId, String password) {
        final String sql = "UPDATE " + TABLE_NAME + " SET password=:password " +
                " WHERE id=:id";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", userId);
        param.put("password", password);
        int updatedCount = namedParameterJdbcTemplate.update(sql, param);
        return updatedCount;
    }

    public User findById(long id) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.BIGINT}
                , new UserMapper());
        return user;
    }
}
