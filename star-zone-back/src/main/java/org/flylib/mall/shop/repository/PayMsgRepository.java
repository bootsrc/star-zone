package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.PayMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PayMsgRepository {
    private static final Logger log = LoggerFactory.getLogger(PayMsgRepository.class);

    private static final String COLUMNS = " msg_id, create_time, title, text, pay_type, amount ";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int add(PayMsg payMsg) {
        String sql = "INSERT INTO pay_msg ( " + COLUMNS + " ) "
                + "VALUES ( "
                + " :msgId,:createTime,:title,:text,:payType,:amount "
                + " )";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("msgId", payMsg.getMsgId());
        parameter.put("createTime", new Timestamp(payMsg.getCreateTime()));
        parameter.put("title", payMsg.getTitle());
        parameter.put("text", payMsg.getText());
        parameter.put("payType", payMsg.getPayType());
        parameter.put("amount", payMsg.getAmount());

        log.info("SQLparameter={}", parameter);
        int updatedCount = namedParameterJdbcTemplate.update(sql, parameter);
        return updatedCount;
    }
}
