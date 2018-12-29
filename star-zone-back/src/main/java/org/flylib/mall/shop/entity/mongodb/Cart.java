package org.flylib.mall.shop.entity.mongodb;

import org.flylib.mall.shop.model.CartItem;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Map;

public class Cart implements Serializable {
    private static final long serialVersionUID = -350082239208001594L;
    @Id
    private long userId;
    private Map<Long, CartItem> cartItemMap;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Map<Long, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Long, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }
}
