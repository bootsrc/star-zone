package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.constant.ShardedTableName;
import org.flylib.mall.shop.entity.Moment;
import org.flylib.mall.shop.entity.MomentLike;
import org.flylib.mall.shop.entity.ShardEntity;
import org.flylib.mall.shop.entity.Topic;
import org.flylib.mall.shop.mapper.MomentLikeMapper;
import org.flylib.mall.shop.mapper.TopicMapper;
import org.flylib.mall.shop.service.TableNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MomentLikeRepository {
    private static final Logger log = LoggerFactory.getLogger(MomentLikeRepository.class);
    private static final String COLUMNS = " id, moment_id, like_user_id, create_time ";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String BASE_TABLE_NAME = ShardedTableName.MOMENT_LIKE;

    @Resource
    private TableNameService tableNameService;

    private String getTableName(long momentId) {
        return tableNameService.getTableName(BASE_TABLE_NAME, momentId);
    }

    public int add(MomentLike momentLike) {
        String tableName = getTableName(momentLike.getMomentId());
        if (momentLike.getCreateTime() == null) {
            log.error("---shardEntity.getCreateTime() == null----");
            return 0;
        }
        String sql = "INSERT INTO " + tableName + " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :id, :momentId, :likeUserId, :createTime "
                + " ) ";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("id", momentLike.getId());
        parameter.put("momentId", momentLike.getMomentId());
        parameter.put("likeUserId", momentLike.getLikeUserId());
        parameter.put("createTime", new Timestamp(momentLike.getCreateTime().getTime()));
//        log.info("SQLparameter={}", parameter);
        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public int delete(long momentId, long id) {
        String tableName = getTableName(momentId);
        int updatedCount = 0;
        String sql = "DELETE FROM " + tableName + " WHERE "
                + "id=:id ";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("id", id);
        updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public MomentLike findExist(long momentId, long userId) {
        String tableName = getTableName(momentId);
        final String sql = "SELECT * FROM " + tableName + " WHERE moment_id=? " +
                " AND like_user_id=? LIMIT 1";
        MomentLike momentLike = null;
        try {
            momentLike = jdbcTemplate.queryForObject(sql, new Object[]{momentId, userId}
                    , new int[]{Types.BIGINT, Types.BIGINT}
                    , new MomentLikeMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return momentLike;
    }

    public int countByMomentId(long momentId) {
        String tableName = getTableName(momentId);
        final String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE moment_id=? ";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, new Object[]{momentId}
                    , new int[]{Types.BIGINT}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
        return count;
    }

    public List<MomentLike> findExistByMomentId(long momentId) {
        String tableName = getTableName(momentId);
        final String sql = "SELECT * FROM " + tableName + " WHERE moment_id=? ";
        List<MomentLike> momentLikeList = null;
        try {
            momentLikeList = jdbcTemplate.query(sql, new Object[]{momentId}
                    , new int[]{Types.BIGINT}
                    , new MomentLikeMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return momentLikeList;
    }
}
