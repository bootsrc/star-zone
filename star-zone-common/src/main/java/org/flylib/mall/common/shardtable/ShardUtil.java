package org.flylib.mall.common.shardtable;

import org.flylib.mall.common.shardtable.hash.HashAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ShardUtil {
    private static final HashAlgorithm alg = HashAlgorithm.KETAMA_HASH;//采用的HASH算法

    /**
     * 沿环的顺时针找到节点
     *
     * @param map
     * @param key
     * @return
     */
    public static String doGetTableName(TreeMap<Long, String> map, String key) {
        final Long hash = alg.hash(key);
        Long target = hash;
        if (!map.containsKey(hash)) {
            target = map.ceilingKey(hash);
            if (target == null && !map.isEmpty()) {
                target = map.firstKey();
            }
        }
        return map.get(target);
    }
}
