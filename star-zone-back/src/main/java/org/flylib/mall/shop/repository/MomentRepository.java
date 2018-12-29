package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.Moment;
import org.flylib.mall.shop.mapper.MomentMapper;
import org.flylib.mall.shop.model.PageParam;
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

@Repository
public class MomentRepository {
    private static final Logger log = LoggerFactory.getLogger(MomentRepository.class);
    private static final String COLUMNS = " id, content, img, create_time, like_count, user_id ";
    private static final String TABLE_NAME = "star_moment";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(Moment moment) {
        if (moment.getCreateTime() < 1){
            log.error("---topic.getCreateTime()<0----");
            return 0;
        }
        String sql = "INSERT INTO " + TABLE_NAME +  " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :id, :content, :img, :createTime, :likeCount, :userId "
                + " ) ";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("id", moment.getId());
        parameter.put("content", moment.getContent());
        parameter.put("img", moment.getImg());
        parameter.put("createTime", new Timestamp(moment.getCreateTime()));
        parameter.put("likeCount", moment.getLikeCount());
        parameter.put("userId", moment.getUserId());

        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public Moment findById(long id) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=? LIMIT 1";
        Moment moment = null;
        try {
            moment = jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.BIGINT}
                    , new MomentMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return moment;
    }

    public List<Moment> byPageForMobile(PageParam pageParam) {
        int start = pageParam.getLimit() * pageParam.getPage();
        final String sql = "SELECT * FROM " + TABLE_NAME
                + " ORDER BY create_time DESC "
                + " LIMIT " + start + ", " + pageParam.getLimit();

        List<Moment> momentList = jdbcTemplate.query(sql, new MomentMapper());
        return momentList;
    }

    public List<Moment> byPageForMobileBySelf(PageParam pageParam, long userId) {
        int start = pageParam.getLimit() * pageParam.getPage();
        final String sql = "SELECT * FROM " + TABLE_NAME
                + " WHERE user_id=? ORDER BY create_time DESC "
                + " LIMIT " + start + ", " + pageParam.getLimit();

        List<Moment> momentList = jdbcTemplate.query(sql
                , new Object[]{userId}
                , new int[]{Types.BIGINT }
                , new MomentMapper());
        return momentList;
    }

    public int delete(long id, long userId) {
        int updatedCount = 0;
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE "
                + "id=:id AND user_id=:userId";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("id", id);
        parameter.put("userId", userId);
        updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }
}
