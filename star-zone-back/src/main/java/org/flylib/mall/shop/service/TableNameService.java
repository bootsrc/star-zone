package org.flylib.mall.shop.service;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.common.shardtable.ShardUtil;
import org.flylib.mall.shop.boot.CustomConfig;
import org.flylib.mall.shop.constant.ShardedTableName;
import org.flylib.mall.shop.singleton.CustomSingleton;
import org.flylib.mall.shop.util.HttpClientUtil;
import org.flylib.mall.shop.util.ShardMysqlUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * fly-dbproxy分表，根据userId定位到对应的表名
 */
@Service
public class TableNameService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TableNameService.class);

    @Autowired
    private CustomConfig customConfig;

    @Resource
    private ShardMysqlService shardMysqlService;

    @Deprecated
    public String getTableNameWithMongo(String name, Long userId){
        String tableName = null;
        String url = customConfig.getTableNameByKeyUrl();
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("name", name);
        params.put("key", userId.toString());
        String responseStr = HttpClientUtil.doPost(url, params, "UTF-8");
        ResponseData responseData = JSON.parseObject(responseStr, ResponseData.class);
        if (responseData != null && responseData.getCode() == ResponseCode.OK) {
            tableName = (String) responseData.getData();
        }
        log.info("---userId:{}-->tableName:{}---", userId, tableName);
        return tableName;
    }

    public String getTableName(String name, long id){
        if (StringUtils.isEmpty(name) || id < 1) {
            return null;
        }
        String tableName = null;
        TreeMap<Long, String> shardMap = null;
        switch (name) {
            case ShardedTableName.ORDER:
                shardMap = CustomSingleton.getInstance().getOrderShardMap();
                break;
            case ShardedTableName.MOMENT_LIKE:
                shardMap = CustomSingleton.getInstance().getMomentLikeShardMap();
                break;
        }

        if (shardMap != null || shardMap.size() > 0){
            String realTableName = ShardUtil.doGetTableName(shardMap, id+"");
            log.info("realTableName={}", realTableName);
            return realTableName;
        }
        return null;
    }
}
