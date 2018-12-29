package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.ChatMsg;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatMsgMapper implements RowMapper<ChatMsg> {
    @Override
    public ChatMsg mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChatMsg chatMsg=new ChatMsg();
        chatMsg.setId(rs.getLong("id"));
        chatMsg.setSenderId(rs.getLong("sender_id"));
        chatMsg.setReceiverId(rs.getLong("receiver_id"));
        chatMsg.setMsgBody(rs.getString("msg_body"));
        chatMsg.setMsgType(rs.getInt("msg_type"));
        chatMsg.setCreateTime(rs.getTimestamp("create_time").getTime());
        return chatMsg;
    }
}
