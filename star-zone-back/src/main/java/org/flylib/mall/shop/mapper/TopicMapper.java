package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.Topic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicMapper implements RowMapper<Topic> {

    @Override
    public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
        Topic topic = new Topic();
        topic.setTopicId(rs.getLong("topic_id"));
        topic.setTitle(rs.getString("title"));
        topic.setImg(rs.getString("img"));
        topic.setIntroduction(rs.getString("introduction"));
        topic.setCreateTime(rs.getTimestamp("create_time"));
        topic.setUpdateTime(rs.getTimestamp("update_time"));
        topic.setContent(rs.getString("content"));
        topic.setStatus(rs.getInt("status"));
        topic.setSerial(rs.getInt("serial"));
        return topic;
    }
}
