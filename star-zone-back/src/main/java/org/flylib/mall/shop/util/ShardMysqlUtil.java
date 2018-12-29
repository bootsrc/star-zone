package org.flylib.mall.shop.util;

import org.flylib.mall.common.shardtable.entity.Shard;
import org.flylib.mall.common.shardtable.hash.ConsistentHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.TreeMap;

public class ShardMysqlUtil {
    private static final Logger log = LoggerFactory.getLogger(ShardMysqlUtil.class);

    public static TreeMap<Long, String>  buildShardMap(String name, Integer count) {
        if (StringUtils.isEmpty(name) || count < 1) {
            log.info("name为空，或count < 1");
            return null;
        }

        ConsistentHash hashObj = new ConsistentHash(name, count);
        hashObj.buildHashRing();
        return hashObj.getMap();
    }
}
