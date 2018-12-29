package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.ShardEntity;
import org.flylib.mall.shop.mapper.ShardEntityMapper;
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
public class ShardMysqlRepository {
    private static final Logger log = LoggerFactory.getLogger(ShardMysqlRepository.class);
    private static final String COLUMNS = " name, count, create_time, update_time ";
    private static final String TABLE_NAME = "fly_shard";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(ShardEntity shardEntity) {
        if (shardEntity.getCreateTime() == null) {
            log.error("---shardEntity.getCreateTime() == null----");
            return 0;
        }
        String sql = "INSERT INTO " + TABLE_NAME + " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :name, :count, :createTime, :updateTime "
                + " ) ";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("name", shardEntity.getName());
        parameter.put("count", shardEntity.getCount());
        parameter.put("createTime", new Timestamp(shardEntity.getCreateTime().getTime()));
        parameter.put("updateTime", new Timestamp(shardEntity.getUpdateTime().getTime()));
//        log.info("SQLparameter={}", parameter);
        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public ShardEntity findByName(String name) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name=? LIMIT 1";
        ShardEntity shard = null;
        try {
            shard = jdbcTemplate.queryForObject(sql, new Object[]{name}, new int[]{Types.VARCHAR}
                    , new ShardEntityMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return shard;
    }


}
