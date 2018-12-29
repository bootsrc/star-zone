package org.flylib.mall.shop.service;

import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.constant.ResponseMsg;
import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.entity.Goods;
import org.flylib.mall.shop.entity.mongodb.Cart;
import org.flylib.mall.shop.model.CartItem;
import org.flylib.mall.shop.repository.CartRepository;
import org.flylib.mall.shop.repository.GoodsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService {
    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    public TableData test() {
        for (int i=0; i< 5; i++) {
            long userId = 1;
            long goodsId = 435238019910336512L;
            long skuId = 1001;
            add(userId, goodsId, skuId + i);
        }
        List<Cart> cartList = cartRepository.findAll();
        TableData tableData =new TableData();
        tableData.setCode(ResponseCode.OK);
        tableData.setMsg(ResponseMsg.OK);
        tableData.setCount(cartList.size());
        tableData.setData(cartList);
        return tableData;
    }

    public void add(long userId, long goodsId, long skuId) {
        Optional<Goods> optionalGoods = goodsRepository.findById(goodsId);
        if (optionalGoods.isPresent()) {
            Goods goods= optionalGoods.get();
            Cart cart = null;

            Optional<Cart> optionalCart = cartRepository.findById(userId);
            if (optionalCart.isPresent()) {
                cart = optionalCart.get();
                Map<Long, CartItem> cartItemMap = cart.getCartItemMap();
                if (cartItemMap.containsKey(skuId)) {
                    int originNumber = cartItemMap.get(skuId).getNum();
                    cartItemMap.get(skuId).setNum(originNumber + 1);
                } else {
                    CartItem cartItem=new CartItem();
                    cartItem.setGoodsId(goodsId);
                    cartItem.setSkuId(skuId);
                    cartItem.setNum(1);
                    cartItem.setName(goods.getName());
                    cartItem.setImg(goods.getImg());
                    // TODO 需要改成sku.getPrice
                    cartItem.setPrice(goods.getPrice());
                    cartItemMap.put(skuId, cartItem);
                }
            } else {
                cart = new Cart();
                cart.setUserId(userId);
                CartItem cartItem=new CartItem();
                cartItem.setGoodsId(goodsId);
                cartItem.setSkuId(skuId);
                cartItem.setNum(1);
                cartItem.setName(goods.getName());
                cartItem.setImg(goods.getImg());
                // TODO 需要改成sku.getPrice
                cartItem.setPrice(goods.getPrice());
                Map<Long, CartItem> cartItemMap = new HashMap<Long, CartItem>();
                cartItemMap.put(skuId, cartItem);
                cart.setCartItemMap(cartItemMap);
            }
            cartRepository.save(cart);
        }
    }
}
