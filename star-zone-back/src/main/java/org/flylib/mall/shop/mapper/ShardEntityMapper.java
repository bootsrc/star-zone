package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.ShardEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShardEntityMapper implements RowMapper<ShardEntity> {
    @Override
    public ShardEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShardEntity shardEntity = new ShardEntity();
        shardEntity.setName(rs.getString("name"));
        shardEntity.setCount(rs.getInt("count"));
        shardEntity.setCreateTime(rs.getTimestamp("create_time"));
        shardEntity.setUpdateTime(rs.getTimestamp("update_time"));
        return shardEntity;
    }
}
