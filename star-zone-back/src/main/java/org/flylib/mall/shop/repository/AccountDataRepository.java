package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.AccountData;
import org.flylib.mall.shop.mapper.AccountDataMapper;
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
public class AccountDataRepository {
    private static final Logger log = LoggerFactory.getLogger(AccountDataRepository.class);
    private static final String COLUMNS = " id, user_id, mi_regid, create_time, update_time ";
    private static final String TABLE_NAME = "account_data";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(AccountData accountData) {
        if (accountData.getCreateTime() < 1){
            log.error("---accountData.getCreateTime()<0----");
            return 0;
        }
        String sql = "INSERT INTO " + TABLE_NAME +  " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :id, :userId, :miRegid, :createTime, :updateTime "
                + " ) ";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("id", accountData.getId());
        parameter.put("userId", accountData.getUserId());
        parameter.put("miRegid", accountData.getMiRegid());
        parameter.put("createTime", new Timestamp(accountData.getCreateTime()));
        parameter.put("updateTime", new Timestamp(accountData.getUpdateTime()));

        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public int update(AccountData accountData) {
        if (accountData.getCreateTime() < 1){
            log.error("---accountData.getCreateTime()<0----");
            return 0;
        }
        String sql = "UPDATE " + TABLE_NAME +  " SET "
                + " user_id=:userId, mi_regid=:miRegid, create_time=:createTime, " +
                "update_time=:updateTime "
                + "  WHERE user_id=:userId";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("id", accountData.getId());
        parameter.put("userId", accountData.getUserId());
        parameter.put("miRegid", accountData.getMiRegid());
        parameter.put("createTime", new Timestamp(accountData.getCreateTime()));
        parameter.put("updateTime", new Timestamp(accountData.getUpdateTime()));

        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public AccountData findByUserId(long userId) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE user_id=? LIMIT 1";
        AccountData accountData = null;
        try {
            accountData = jdbcTemplate.queryForObject(sql,
                    new Object[]{userId}, new int[]{Types.BIGINT}
                    , new AccountDataMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return accountData;
    }
}
