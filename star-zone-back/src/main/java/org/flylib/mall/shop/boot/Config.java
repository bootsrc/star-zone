package org.flylib.mall.shop.boot;

import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(CustomConfig customConfig) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(customConfig.getWorkerId()
                , customConfig.getDatacenterId());
        return idWorker;
    }
}
