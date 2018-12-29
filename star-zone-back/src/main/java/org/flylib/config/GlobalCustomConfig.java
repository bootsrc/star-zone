package org.flylib.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class GlobalCustomConfig {
    @Value("${custom.jedisPoolConfig.maxIdle}")
    private int jedisPoolConfigMaxIdle;
    @Value("${custom.jedisPoolConfig.maxTotal}")
    private int jedisPoolConfigMaxTotal;
    @Value("${custom.jedisPoolConfig.testOnBorrow}")
    private boolean jedisPoolConfigTestOnBorrow;

    @Value("${custom.redis.host}")
    private String redisHost;
    @Value("${custom.redis.port}")
    private int redisPort;
    @Value("${custom.redis.timeout}")
    private int redisTimeout;
    @Value("${custom.redis.password}")
    private String redisPassword;
    @Value("${custom.redis.database}")
    private int redisDatabase;
    @Value("${custom.resourcePath}")
    private String resourcePath;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(jedisPoolConfigMaxIdle);
        jedisPoolConfig.setMaxTotal(jedisPoolConfigMaxTotal);
        jedisPoolConfig.setTestOnBorrow(jedisPoolConfigTestOnBorrow);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisPort, redisTimeout
                , redisPassword, redisDatabase);
        return jedisPool;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
