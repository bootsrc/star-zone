package org.flylib.mall.shop.controller;

import org.flylib.mall.shop.service.PushService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("push")
public class PushController {
    @Resource
    private PushService pushService;

    @RequestMapping("test")
    public String test(String regId){
        pushService.test(regId);
        return "TestPushDone";
    }

    @RequestMapping("sendBroadcastForUpgrade")
    public String sendBroadcastForUpgrade() {
        pushService.sendBroadcastForUpgrade();
        return "sendBroadcastForUpgrade_done";
    }
}
