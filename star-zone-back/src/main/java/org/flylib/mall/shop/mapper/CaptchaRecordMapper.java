package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.CaptchaRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CaptchaRecordMapper implements RowMapper<CaptchaRecord> {
    @Override
    public CaptchaRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        CaptchaRecord captchaRecord = new CaptchaRecord();
        captchaRecord.setId(rs.getLong("id"));
        captchaRecord.setMobile(rs.getString("mobile"));
        captchaRecord.setCaptcha(rs.getString("captcha"));
        captchaRecord.setCreateTime(rs.getTimestamp("create_time"));
        captchaRecord.setExpireTime(rs.getTimestamp("expire_time"));
        return captchaRecord;
    }
}
