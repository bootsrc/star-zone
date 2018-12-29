package org.flylib.mall.shop.controller;

import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.shop.service.ShardMysqlService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("ops")
public class OpsController {

    @Resource
    private ShardMysqlService shardMysqlService;

    @RequestMapping("addShardConfig")
    public ResponseData initShardConfig(){
        shardMysqlService.addShardConfig();
        ResponseData responseData=ResponseData.newOK();
        return responseData;
    }
}
