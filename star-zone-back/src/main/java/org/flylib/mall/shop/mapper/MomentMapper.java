package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.Moment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MomentMapper implements RowMapper<Moment> {

    @Override
    public Moment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Moment moment = new Moment();

        moment.setId(rs.getLong("id"));
        moment.setContent(rs.getString("content"));
        moment.setImg(rs.getString("img"));
        Date timestamp = rs.getTimestamp("create_time");
        moment.setCreateTime(timestamp.getTime());
        moment.setLikeCount(rs.getInt("like_count"));
        moment.setUserId(rs.getLong("user_id"));
        return moment;
    }
}
