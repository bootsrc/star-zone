package org.flylib.mall.shop.repository;

import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.constant.ResponseMsg;
import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.entity.Topic;
import org.flylib.mall.shop.mapper.TopicMapper;
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
public class TopicRepository {
    private static final Logger log = LoggerFactory.getLogger(TopicRepository.class);
    private static final String COLUMNS = " topic_id, title, img, introduction, create_time " +
            " , update_time, content, status, serial ";
    private static final String TABLE_NAME = "star_topic";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(Topic topic) {
        if (topic.getCreateTime() == null) {
            log.error("---topic.getCreateTime() == null----");
            return 0;
        }
        String sql = "INSERT INTO " + TABLE_NAME + " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :topicId, :title, :img, :introduction, :createTime, :updateTime, :content" +
                ", :status, :serial "
                + " ) ";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("topicId", topic.getTopicId());
        parameter.put("title", topic.getTitle());
        parameter.put("img", topic.getImg());
        parameter.put("introduction", topic.getIntroduction());
        parameter.put("createTime", new Timestamp(topic.getCreateTime().getTime()));
        parameter.put("updateTime", new Timestamp(topic.getUpdateTime().getTime()));
        parameter.put("content", topic.getContent());
        parameter.put("status", topic.getStatus());
        parameter.put("serial", topic.getSerial());
//        log.info("SQLparameter={}", parameter);
        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public Topic findById(long id) {
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE topic_id=? LIMIT 1";
        Topic topic = null;
        try {
            topic = jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.BIGINT}
                    , new TopicMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return topic;
    }

    public int update(Topic topic) {
        int updatedCount = 0;

        String sql = "UPDATE " + TABLE_NAME + " SET "
                + " title=:title, img=:img, introduction=:introduction, create_time=:createTime " +
                ", update_time=:updateTime, content=:content, status=:status, serial=:serial "
                + " WHERE topic_id=:topicId ";

        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("topicId", topic.getTopicId());
        parameter.put("title", topic.getTitle());
        parameter.put("img", topic.getImg());
        parameter.put("introduction", topic.getIntroduction());
        parameter.put("createTime", new Timestamp(topic.getCreateTime().getTime()));
        parameter.put("updateTime", new Timestamp(topic.getUpdateTime().getTime()));
        parameter.put("content", topic.getContent());
        parameter.put("status", topic.getStatus());
        parameter.put("serial", topic.getSerial());

//        log.info("SQLparameter={}", parameter);
        updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public int countVisible() {
        String countSql = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE status='1' ";
        int count = jdbcTemplate.queryForObject(countSql, Integer.class);
        return count;
    }

    public int count() {
        String countSql = "SELECT COUNT(*) FROM " + TABLE_NAME;
        int count = jdbcTemplate.queryForObject(countSql, Integer.class);
        return count;
    }

    public List<Topic> byPageVisible(PageParam pageParam) {
        int start = pageParam.getLimit() * pageParam.getPage();
        final String sql = "SELECT * FROM " + TABLE_NAME
                + " WHERE status='1' ORDER BY serial "
                + " LIMIT " + start + ", " + pageParam.getLimit();

        List<Topic> topicList = jdbcTemplate.query(sql, new TopicMapper());
        return topicList;
    }

    public TableData<Topic> byPage(PageParam pageParam) {
        TableData<Topic> topicTableData = new TableData<Topic>();
        int count = count();

        int start = pageParam.getLimit() * pageParam.getPage();
        final String sql = "SELECT * FROM " + TABLE_NAME
                + " ORDER BY serial LIMIT " + start + ", " + pageParam.getLimit();

        List<Topic> topicList = jdbcTemplate.query(sql, new TopicMapper());

        topicTableData.setCode(ResponseCode.OK);
        topicTableData.setMsg(ResponseMsg.OK);
        topicTableData.setCount(count);
        topicTableData.setData(topicList);
        return topicTableData;
    }

    public int delete(long id) {
        int updatedCount = 0;
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE "
                + "topic_id=:id";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("id", id);
        updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }
}
