package org.flylib.mall.shop.service;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.shop.constant.ShardedTableName;
import org.flylib.mall.shop.entity.ShardEntity;
import org.flylib.mall.shop.repository.ShardMysqlRepository;
import org.flylib.mall.shop.singleton.CustomSingleton;
import org.flylib.mall.shop.util.ShardMysqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.TreeMap;

@Service
public class ShardMysqlService {
    private static final Logger log = LoggerFactory.getLogger(ShardMysqlService.class);

    @Resource
    private ShardMysqlRepository shardMysqlRepository;

    private ResponseData add(ShardEntity shardEntity) {
        ResponseData responseData = new ResponseData();
        /**
         * responseCode = 2;系统错误
         */
        int responseCode = 2;
        String responseMsg = "系统错误";

        if (shardEntity != null && !StringUtils.isEmpty(shardEntity.getName())) {
            String name = shardEntity.getName();
            ShardEntity existShardEntity = shardMysqlRepository.findByName(name);
            Date nowTime = new Date();
            if (existShardEntity == null) {
                shardEntity.setCreateTime(nowTime);
                shardEntity.setUpdateTime(nowTime);
                int updatedCount = shardMysqlRepository.add(shardEntity);
                if (updatedCount == 1) {
                    responseCode = ResponseCode.OK;
                    responseMsg = "操作成功";
                } else {
                    responseCode = 3;
                    responseMsg = "插入表shard失败";
                }
            } else {
                log.info("ThisTopicExists");
                responseCode = 4;
                responseMsg = "shard记录已经存在，insert shard表失败";
            }
        }

        responseData.setCode(responseCode);
        responseData.setMsg(responseMsg);
        log.info("addShardMysql_result={}", JSON.toJSONString(responseData));
        return responseData;
    }

    public ShardEntity findByName(String name){
        return shardMysqlRepository.findByName(name);
    }

    public void addShardConfig() {
        addOrderShard();
        addMomentLikeShard();
    }

    private void addOrderShard(){
        ShardEntity orderShardEntity = new ShardEntity();
        orderShardEntity.setName("order");
        orderShardEntity.setCount(4);
        ResponseData responseData = add(orderShardEntity);
    }

    private void addMomentLikeShard(){
        ShardEntity orderShardEntity = new ShardEntity();
        orderShardEntity.setName("moment_like");
        orderShardEntity.setCount(30);
        ResponseData responseData = add(orderShardEntity);
    }

    public TreeMap<Long, String> buildShardMap(String name) {
        ShardEntity shardEntity=findByName(name);
        if (shardEntity==null || shardEntity.getCount() < 0) {
            return null;
        }
        return ShardMysqlUtil.buildShardMap(name, shardEntity.getCount());
    }

    public void buildShardMapForAll() {
        TreeMap<Long, String> momentLikeMap = buildShardMap(ShardedTableName.MOMENT_LIKE);
        CustomSingleton.getInstance().setMomentLikeShardMap(momentLikeMap);

        TreeMap<Long, String> orderMap = buildShardMap(ShardedTableName.ORDER);
        CustomSingleton.getInstance().setOrderShardMap(orderMap);
    }
}
