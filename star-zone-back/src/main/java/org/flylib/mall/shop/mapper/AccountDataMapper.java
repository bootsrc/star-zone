package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.AccountData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDataMapper implements RowMapper<AccountData> {
    @Override
    public AccountData mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountData accountData = new AccountData();
        accountData.setId(rs.getLong("id"));
        accountData.setUserId(rs.getLong("user_id"));
        accountData.setMiRegid(rs.getString("mi_regid"));
        accountData.setCreateTime(rs.getTimestamp("create_time").getTime());
        accountData.setUpdateTime(rs.getTimestamp("update_time").getTime());
        return accountData;
    }
}
