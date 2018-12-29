package org.flylib.mall.shop.service;

import org.aspectj.weaver.ast.Or;
import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.common.dto.OrderDTO;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.entity.Order;
import org.flylib.mall.shop.repository.OrderRepository;
import org.flylib.mall.shop.util.ObjectConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    public void add(OrderDTO orderDTO) {
        long orderId = snowflakeIdWorker.nextId();
        Order order = ObjectConverter.orderDTO2Entity(orderDTO);
        order.setId(orderId);
        orderRepository.add(order);
    }

    public void add(Order order) {
        orderRepository.add(order);
    }

    public void test() {
        Order order = new Order();
        long orderId = snowflakeIdWorker.nextId();
        long userId = 1;
        order.setId(orderId);
        order.setUserId(userId);
        order.setAddress("南京东路1号");
        Date createTime = new Date();
        order.setCreateTime(createTime);
        order.setUpdateTime(createTime);
        order.setConsignee("刘先生");
        order.setConsigneeMoblie("13100000000");
        order.setRemark("test remark");
        order.setProvince("上海市");
        order.setCity("上海市");
        order.setDistrict("黄浦区");
        order.setExpressId("000011111999999");
        order.setDeliverTime(null);
        order.setConfirmTime(null);
        order.setDeliveryWay(0);
        order.setCartItemList("");
        order.setDiscountList("");
        order.setAmount(2000);
        order.setPostage(200);
        order.setShouldPay(2200);
        add(order);
    }

    public TableData findAll(long userId, int page, int size) {
        TableData tableData = orderRepository.findAll(userId, page, size);
        return tableData;
    }
}
