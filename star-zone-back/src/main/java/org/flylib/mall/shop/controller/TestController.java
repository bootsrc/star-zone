package org.flylib.mall.shop.controller;

import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.shop.constant.ShardedTableName;
import org.flylib.mall.shop.service.MomentLikeService;
import org.flylib.mall.shop.service.ShardMysqlService;
import org.flylib.mall.shop.service.TableNameService;
import org.flylib.passport.service.TokenRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private TokenRedisService tokenRedisService;

    @Resource
    private ShardMysqlService shardMysqlService;

    @Resource
    private TableNameService tableNameService;

    @RequestMapping("testRedis")
    public ResponseData testRedis(){
        tokenRedisService.test();
        ResponseData responseData=ResponseData.newOK();
        return responseData;
    }

    @RequestMapping("testInteceptor")
    public ResponseData testInteceptor(){
        ResponseData responseData=ResponseData.newOK();
        return responseData;
    }

    @RequestMapping("testShard")
    public ResponseData testShard(){
        String tableName = tableNameService.getTableName(ShardedTableName.MOMENT_LIKE, 464464690391547904L);
        ResponseData responseData=ResponseData.newOK();
        responseData.setData(tableName);
        return responseData;
    }
}
