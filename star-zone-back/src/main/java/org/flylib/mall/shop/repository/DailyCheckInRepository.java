package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.DailyCheckIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DailyCheckInRepository {

    private static final Logger log = LoggerFactory.getLogger(DailyCheckInRepository.class);
    private static final String COLUMNS = " id, user_id, create_time ";
    private static final String TABLE_NAME = "daily_check_in";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(DailyCheckIn dailyCheckIn) {
        String sql = "INSERT INTO " + TABLE_NAME + " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :id, :userId, :createTime "
                + " ) ";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("id", dailyCheckIn.getId());
        parameter.put("userId", dailyCheckIn.getUserId());
        parameter.put("createTime", dailyCheckIn.getCreateTime());

        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public Date getLatestTime(long userId) {
        String sql = "SELECT MAX(create_time) FROM " + TABLE_NAME + " WHERE user_id=? ";
        Date date = null;

        try {
            date = jdbcTemplate.queryForObject(sql, new Object[]{userId}, new int[]{Types.BIGINT},
                    Date.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return date;
    }
}
