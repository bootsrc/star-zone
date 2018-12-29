package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setCreateTime(rs.getTimestamp("create_time"));
        user.setUpdateTime(rs.getTimestamp("update_time"));
        user.setStatus(rs.getInt("status"));
        user.setPassword(rs.getString("password"));
        user.setMobile(rs.getString("mobile"));
        return user;
    }
}
