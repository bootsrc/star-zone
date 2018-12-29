package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("id"));
        comment.setCommentText(rs.getString("comment_text"));
        comment.setCreateTime(rs.getTimestamp("create_time").getTime());
        comment.setMomentId(rs.getLong("moment_id"));
        comment.setUserId(rs.getLong("user_id"));
        return comment;
    }
}
