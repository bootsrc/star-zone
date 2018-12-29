package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.CaptchaRecord;
import org.flylib.mall.shop.mapper.CaptchaRecordMapper;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CaptchaRecordRepository {
    private static final Logger log = LoggerFactory.getLogger(CaptchaRecordRepository.class);
    private static final String COLUMNS = " id, mobile, captcha, create_time, expire_time ";
    private static final String TABLE_NAME = "captcha_record";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(CaptchaRecord captchaRecord) {
        if (captchaRecord.getCreateTime() == null || captchaRecord.getExpireTime() == null){
            log.error("---captchaRecord.getCreateTime() == null, OR captchaRecord.getExpireTime() == null----");
            return 0;
        }
        String sql = "INSERT INTO " + TABLE_NAME +  " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :id, :mobile, :captcha, :createTime, :expireTime "
                + " )";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("id", captchaRecord.getId());
        parameter.put("mobile", captchaRecord.getMobile());
        parameter.put("captcha", captchaRecord.getCaptcha());
        parameter.put("createTime", new Timestamp(captchaRecord.getCreateTime().getTime()));
        parameter.put("expireTime", new Timestamp(captchaRecord.getExpireTime().getTime()));
        log.info("SQLparameter={}", parameter);
        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public CaptchaRecord findById(long id) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        CaptchaRecord captchaRecord = null;
        try {
            captchaRecord = jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.BIGINT}
                    , new CaptchaRecordMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return captchaRecord;
    }

    public CaptchaRecord findLatestByMobile(String mobile) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE mobile=? " +
                " ORDER BY expire_time DESC LIMIT 1";
        CaptchaRecord captchaRecord = null;
        try {
            captchaRecord = jdbcTemplate.queryForObject(sql, new Object[] {mobile},
                    new int[] {Types.VARCHAR },
                    new CaptchaRecordMapper());
        } catch (EmptyResultDataAccessException e){
            captchaRecord = null;
        }

        return captchaRecord;
    }
}
