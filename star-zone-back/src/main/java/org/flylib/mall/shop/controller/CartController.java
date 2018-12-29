package org.flylib.mall.shop.controller;

import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("test")
    public TableData test() {
        return cartService.test();
    }
}
