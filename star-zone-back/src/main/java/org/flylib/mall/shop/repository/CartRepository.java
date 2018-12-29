package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.mongodb.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, Long> {
}
