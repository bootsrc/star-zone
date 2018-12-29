package org.flylib.mall.shop.controller;

import org.flylib.mall.common.constant.ResponseMsg;
import org.flylib.mall.common.dto.OrderDTO;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.entity.Order;
import org.flylib.mall.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.RectangularShape;
import java.util.List;
import java.util.Map;

@RequestMapping("order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("list")
    @ResponseBody
    public TableData list(long userId, int page, int limit){
        page = page < 1 ? 0 : page -1;
        limit = limit < 0 ? 10 : limit;
        return orderService.findAll(userId, page, limit);
    }

//    @RequestMapping(value = "saveProfile", consumes = "application/json;charset=UTF-8")
//    public ResponseData saveProfile(@RequestBody OrderDTO orderDTO){
//        orderService.saveProfile(orderDTO);
//        ResponseData responseData = ResponseData.newOK();
//        return responseData;
//    }

    @RequestMapping("test")
    @ResponseBody
    public ResponseData test(){
        orderService.test();
        ResponseData responseData = ResponseData.newOK();
        return responseData;
    }
}
