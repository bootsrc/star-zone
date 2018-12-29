package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.MomentLike;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MomentLikeMapper implements RowMapper<MomentLike> {
    @Override
    public MomentLike mapRow(ResultSet rs, int rowNum) throws SQLException {
        MomentLike momentLike=new MomentLike();
        momentLike.setId(rs.getLong("id"));
        momentLike.setMomentId(rs.getLong("moment_id"));
        momentLike.setLikeUserId(rs.getLong("like_user_id"));
        momentLike.setCreateTime(rs.getTimestamp("create_time"));
        return momentLike;
    }
}
