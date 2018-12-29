package org.flylib.mall.shop.singleton;

import org.flylib.mall.shop.mapper.BaseGoodsMapper;

import java.util.TreeMap;

public class CustomSingleton {
    private static CustomSingleton ourInstance = new CustomSingleton();

    public static CustomSingleton getInstance() {
        return ourInstance;
    }

    private CustomSingleton() {
        baseGoodsMapper = new BaseGoodsMapper();
    }

    private BaseGoodsMapper baseGoodsMapper;

    public TreeMap<Long, String> getMomentLikeShardMap() {
        return momentLikeShardMap;
    }

    public void setMomentLikeShardMap(TreeMap<Long, String> momentLikeShardMap) {
        this.momentLikeShardMap = momentLikeShardMap;
    }

    public TreeMap<Long, String> getOrderShardMap() {
        return orderShardMap;
    }

    public void setOrderShardMap(TreeMap<Long, String> orderShardMap) {
        this.orderShardMap = orderShardMap;
    }

    private TreeMap<Long, String> momentLikeShardMap = new TreeMap<Long, String>();
    private TreeMap<Long, String> orderShardMap = new TreeMap<Long, String>();

    public BaseGoodsMapper getBaseGoodsMapper() {
        return baseGoodsMapper;
    }


}
