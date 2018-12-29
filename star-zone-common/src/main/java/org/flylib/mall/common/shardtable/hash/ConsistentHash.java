package org.flylib.mall.common.shardtable.hash;

import org.flylib.mall.common.util.AppjishuStrUtil;

import java.util.TreeMap;

/**
 * @author liushaoming
 * 一致性hash的实现
 * Java implementation of consistent-hashing
 */
public class ConsistentHash {
    private HashAlgorithm alg = HashAlgorithm.KETAMA_HASH;//采用的HASH算法
    private final TreeMap<Long, String> map = new TreeMap<Long, String>();
    private String name;
    private Integer count;

    public ConsistentHash(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    /**
     * 构建一致性HASH环
     */
    public void buildHashRing() {
        if (AppjishuStrUtil.isEmpty(name)) return;
        else {
            if (map == null || map.size() ==0){
                for (int i = 0; i < count; i++) {
                    String tableName = name + "_" + i;
                    long nodeKey = this.alg.hash(tableName);
                    map.put(nodeKey, tableName);
                }
            }
        }
    }

    /**
     * 沿环的顺时针找到节点
     *
     * @param key
     * @return
     */
    public String getTableNameByKey(String key) {
        final Long hash = this.alg.hash(key);
        Long target = hash;
        if (!map.containsKey(hash)) {
            target = map.ceilingKey(hash);
            if (target == null && !map.isEmpty()) {
                target = map.firstKey();
            }
        }
        return map.get(target);
    }

    /**
     * 设置一致性HASH的算法，默认采用 KETAMA_HASH
     * 对于一致性HASH而言选择的HASH算法首先要考虑发散度其次再考虑性能
     *
     * @param alg 具体支持的算法
     * @see HashAlgorithm
     */
    public void setAlg(HashAlgorithm alg) {
        this.alg = alg;
    }

    public TreeMap<Long, String> getMap() {
        return map;
    }
}
