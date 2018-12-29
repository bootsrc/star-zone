package org.flylib.mall.shop.mapper;

import org.flylib.mall.shop.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setUserId(rs.getLong("user_id"));
        order.setAddress(rs.getString("address"));
        order.setCreateTime(rs.getTimestamp("create_time"));
        order.setUpdateTime(rs.getTimestamp("update_time"));
        order.setConsignee(rs.getString("consignee"));
        order.setConsigneeMoblie(rs.getString("consignee_moblie"));
        order.setRemark(rs.getString("remark"));
        order.setProvince(rs.getString("province"));
        order.setCity(rs.getString("city"));
        order.setDistrict(rs.getString("district"));
        order.setExpressId(rs.getString("express_id"));
        order.setDeliverTime(rs.getTimestamp("deliver_time"));
        order.setConfirmTime(rs.getTimestamp("confirm_time"));
        order.setDeliveryWay(rs.getInt("delivery_way"));
        order.setCartItemList(rs.getString("cart_item_list"));
        order.setDiscountList(rs.getString("discount_list"));
        order.setAmount(rs.getLong("amount"));
        order.setPostage(rs.getLong("postage"));
        order.setShouldPay(rs.getLong("should_pay"));
        return order;
    }
}
