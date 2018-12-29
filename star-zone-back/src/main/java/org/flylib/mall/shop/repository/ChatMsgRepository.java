package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.ChatMsg;
import org.flylib.mall.shop.mapper.ChatMsgMapper;
import org.flylib.mall.shop.model.ChatUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChatMsgRepository {

    private static final Logger log = LoggerFactory.getLogger(ChatMsgRepository.class);
    private static final String COLUMNS = " id, sender_id, receiver_id, msg_body, msg_type " +
            ", create_time ";
    private static final String TABLE_NAME = "chat_msg";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(ChatMsg chatMsg) {
        if (chatMsg.getCreateTime() < 1){
            log.error("---chatMsg.getCreateTime()<0----");
            return 0;
        }
        String sql = "INSERT INTO " + TABLE_NAME +  " ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :id, :senderId, :receiverId, :msgBody, :msgType, :createTime "
                + " ) ";
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("id", chatMsg.getId());
        parameter.put("senderId", chatMsg.getSenderId());
        parameter.put("receiverId", chatMsg.getReceiverId());
        parameter.put("msgBody", chatMsg.getMsgBody());
        parameter.put("msgType", chatMsg.getMsgType());
        parameter.put("createTime", new Timestamp(chatMsg.getCreateTime()));

        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }

    public List<ChatMsg> msgList(long userId, long targetUserId) {
        String sql = "SELECT * FROM " + TABLE_NAME +
                " WHERE ( sender_id=:userId AND receiver_id=:targetUserId ) " +
                " OR  ( sender_id=:targetUserId AND  receiver_id=:userId ) " +
                " ORDER BY create_time DESC LIMIT 50 ";

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userId", userId);
        parameter.put("targetUserId", targetUserId);
        List<ChatMsg> msgList = namedParameterJdbcTemplate.query(sql, parameter, new ChatMsgMapper());
        return msgList;
    }

    public List<ChatMsg> listByUserId(long userId) {
        String sql = "SELECT t.sender_id, MAX(create_time) create_time FROM ( " +
                "SELECT * FROM " + TABLE_NAME +" WHERE sender_id=:userId "
                + " OR receiver_id=:userId " +
                " ORDER BY create_time DESC ) t GROUP BY sender_id ";


        return null;
    }

    public List<ChatUser> recentUser0(long userId) {
        String sql = "SELECT receiver_id user_id, MAX(create_time) create_time " +
                "FROM " + TABLE_NAME + " WHERE sender_id =:userId " +
                "GROUP BY receiver_id";
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", userId);
        List<ChatUser> list = namedParameterJdbcTemplate.query(sql, parameter, new BeanPropertyRowMapper<>(ChatUser.class));
        return list;
    }

    public List<ChatUser> recentUser1(long userId) {
        String sql = "SELECT sender_id user_id, MAX(create_time) create_time " +
                "FROM " + TABLE_NAME + " WHERE receiver_id =:userId " +
                "GROUP BY sender_id";
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", userId);
        List<ChatUser> list = namedParameterJdbcTemplate.query(sql, parameter, new BeanPropertyRowMapper<>(ChatUser.class));
        return list;
    }

    public ChatMsg getLatestMsg(long userId, long targetId, Date createTime){
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE create_time=:createTime AND ((sender_id=:userId " +
                " AND receiver_id=:targetId) OR "
                + "( sender_id=:targetId AND receiver_id=:userId )) LIMIT 1";
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", userId);
        parameter.put("targetId", targetId);
        parameter.put("createTime", new Timestamp(createTime.getTime()));

        List<ChatMsg> resultList = namedParameterJdbcTemplate.query(sql,
                parameter, new ChatMsgMapper());

        if (resultList == null || resultList.size()==0) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

}
