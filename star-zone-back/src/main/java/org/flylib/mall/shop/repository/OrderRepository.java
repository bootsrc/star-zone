package org.flylib.mall.shop.repository;

import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.constant.ResponseMsg;
import org.flylib.mall.common.constant.ShardName;
import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.entity.Order;
import org.flylib.mall.shop.mapper.OrderMapper;
import org.flylib.mall.shop.service.TableNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.List;

/**
 * 订单Order采用了分表
 */
@Repository
public class OrderRepository {
    private static final Logger log = LoggerFactory.getLogger(OrderRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TableNameService tableNameService;

    public int add(Order order) {
        String tableName = tableNameService.getTableName(ShardName.ORDER, order.getUserId());
        if (StringUtils.isEmpty(tableName)) {
            return -1;
        }
        final String sql = "INSERT INTO " + tableName + " (id, user_id, address, create_time" +
                ", update_time, consignee, consignee_moblie, remark, province, city" +
                ", district, express_id, deliver_time, confirm_time, delivery_way, cart_item_list" +
                ", discount_list, amount, postage, should_pay) values "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        log.info("---sqlStr:{}", sql);
        int updatedCount = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql
                        , Statement.NO_GENERATED_KEYS);
                ps.setLong(1, order.getId());
                ps.setLong(2, order.getUserId());
                ps.setString(3, order.getAddress());
                ps.setTimestamp(4, new Timestamp(order.getCreateTime().getTime()));
                ps.setTimestamp(5, new Timestamp(order.getUpdateTime().getTime()));
                ps.setString(6, order.getConsignee());
                ps.setString(7, order.getConsigneeMoblie());
                ps.setString(8, order.getRemark());
                ps.setString(9, order.getProvince());
                ps.setString(10, order.getCity());
                ps.setString(11, order.getDistrict());
                ps.setString(12, order.getExpressId());
                ps.setTimestamp(13, null);
                ps.setTimestamp(14, null);
                ps.setInt(15, order.getDeliveryWay());
                ps.setString(16, order.getCartItemList());
                ps.setString(17, order.getDiscountList());
                ps.setLong(18, order.getAmount());
                ps.setLong(19, order.getPostage());
                ps.setLong(20, order.getShouldPay());
                return ps;
            }
        });

        return updatedCount;
    }

    public TableData findAll(long userId, int page, int size) {
        String tableName = tableNameService.getTableName(ShardName.ORDER, userId);
        if (StringUtils.isEmpty(tableName)) {
            TableData tableData = new TableData();
            tableData.setCode(500);
            tableData.setMsg("Data TableName is empty");
            tableData.setCount(0);
            return tableData;
        }

        final String sql = "SELECT * FROM " + tableName
                + " WHERE user_id=? ORDER BY create_time DESC LIMIT ?, ?";
        log.info("---sqlStr:{}", sql);
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql
                        , Statement.NO_GENERATED_KEYS);
                ps.setLong(1, userId);
                ps.setInt(2, page);
                ps.setInt(3, size);
                return ps;
            }
        };
        List<Order> orderList = jdbcTemplate.query(creator, new OrderMapper());

        String countSql = "SELECT COUNT(*) FROM " + tableName;

        int count = jdbcTemplate.queryForObject(countSql, Integer.class);

        TableData tableData = new TableData();
        tableData.setCode(ResponseCode.OK);
        tableData.setMsg(ResponseMsg.OK);
        tableData.setCount(count);
        tableData.setData(orderList);
        return tableData;
    }
}
