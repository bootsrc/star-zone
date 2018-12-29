package org.flylib.mall.shop.boot;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.shop.service.ShardMysqlService;
import org.flylib.mall.shop.singleton.CustomSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyApplicationRunner implements ApplicationRunner, Ordered {
    private static final Logger log = LoggerFactory.getLogger(MyApplicationRunner.class);

    @Resource
    private ShardMysqlService shardMysqlService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        shardMysqlService.buildShardMapForAll();
        log.info("MyApplicationRunner--run.");

        String str0 = JSON.toJSONString(CustomSingleton.getInstance().getMomentLikeShardMap());
        String str1 = JSON.toJSONString(CustomSingleton.getInstance().getOrderShardMap());
        log.info("getMomentLikeShardMap={}" , str0);
        log.info("-----------------------");
        log.info("getOrderShardMap={}", str1);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
