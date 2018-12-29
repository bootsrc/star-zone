package org.flylib.mall.shop.controller;

import org.flylib.mall.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class IndexController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("")
    public String index() {
        return "OK";
    }

    @RequestMapping("test111")
    public String test(){
        orderService.test();
        return "test ok";
    }
}
