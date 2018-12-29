package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.Comment;
import org.flylib.mall.shop.mapper.CommentMapper;
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
public class CommentRepository {
    private static final Logger log = LoggerFactory.getLogger(CommentRepository.class);
    private static final String COLUMNS = " id, comment_text, create_time, moment_id, user_id ";
    private static final String TABLE_NAME = "star_comment";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(Comment comment) {
        if (comment.getCreateTime() < 1) {
            log.error("---topic.getCreateTime()<0----");
            return 0;
        }
        String sql = "INSERT INTO " + TABLE_NAME + " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :id, :commentText, :createTime, :momentId, :userId "
                + " ) ";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("id", comment.getId());
        parameter.put("commentText", comment.getCommentText());
        parameter.put("createTime", new Timestamp(comment.getCreateTime()));
        parameter.put("momentId", comment.getMomentId());
        parameter.put("userId", comment.getUserId());
        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public Comment findById(long id) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=? LIMIT 1";
        Comment comment = null;
        try {
            comment = jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.BIGINT}
                    , new CommentMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return comment;
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

    public int deleteAll(long momentId) {
        int updatedCount = 0;
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE "
                + " moment_id=:momentId";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("momentId", momentId);
        updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public List<Comment> listByMomentId(long momentId) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE moment_id=? "
                + " ORDER BY create_time ASC ";

        List<Comment> commentList = jdbcTemplate.query(sql,
                new Object[]{momentId}, new int[]{Types.BIGINT}
                , new CommentMapper());
        return commentList;
    }

    public int countByMomentId(long momentId) {
        int count;
        final String sql = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE moment_id=?";
        try {
            count = jdbcTemplate.queryForObject(sql, new Object[]{momentId}, new int[]{Types.BIGINT}
                    , Integer.class);
        } catch (EmptyResultDataAccessException e) {
            count = 0;
        }
        return count;
    }
}
