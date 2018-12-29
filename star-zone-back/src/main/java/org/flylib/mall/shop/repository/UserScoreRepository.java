package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.UserScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserScoreRepository {
    private static final Logger log = LoggerFactory.getLogger(DailyCheckInRepository.class);
    private static final String COLUMNS = " user_id, check_in_count, score ";
    private static final String TABLE_NAME = "user_score";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(UserScore userScore) {
        String sql = "INSERT INTO " + TABLE_NAME + " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :userId, :checkInCount, :score "
                + " ) ";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userId", userScore.getUserId());
        parameter.put("checkInCount", userScore.getCheckInCount());
        parameter.put("score", userScore.getScore());

        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public int update(UserScore userScore) {
        String sql = "UPDATE " + TABLE_NAME + " SET "
                + " check_in_count=:checkInCount, score=:score "
                + " WHERE user_id=:userId";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("checkInCount", userScore.getCheckInCount());
        parameter.put("score", userScore.getScore());
        parameter.put("userId", userScore.getUserId());

        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public UserScore findOne(long userId) {
        String sql = " SELECT * FROM " + TABLE_NAME + " WHERE user_id=:userId ";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userId", userId);
        UserScore userScore = null;

        try {
            userScore = namedParameterJdbcTemplate.queryForObject(sql, parameter,
                    new BeanPropertyRowMapper<>(UserScore.class));
        } catch (EmptyResultDataAccessException e) {
            userScore = null;
        }
        return userScore;
    }
}
