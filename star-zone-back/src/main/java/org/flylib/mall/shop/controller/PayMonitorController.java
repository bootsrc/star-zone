package org.flylib.mall.shop.controller;

import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.shop.entity.PayMsg;
import org.flylib.mall.shop.service.PayMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("pay-monitor")
public class PayMonitorController {
    private static final Logger log = LoggerFactory.getLogger(PayMonitorController.class);

    @Autowired
    private PayMsgService payMsgService;

//    @RequestMapping(value = "notify", consumes = "")
//    public Object notify(String title, String content) {
//        log.info("====notify_body --> title={},content={}", title, content);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("code", 1);
//        return map;
//    }

    @RequestMapping(value = "notify", consumes = "application/json;charset=UTF-8")
    public Object notify(@RequestBody Map param) {
        String title = (String) param.get("title");
        String text = (String) param.get("text");
        log.info("====notify_body --> title={},text={}", title, text);

        PayMsg payMsg=new PayMsg();
        payMsg.setTitle(title);
        payMsg.setText(text);
        payMsgService.add(payMsg);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", ResponseCode.OK);
        return map;
    }
}
