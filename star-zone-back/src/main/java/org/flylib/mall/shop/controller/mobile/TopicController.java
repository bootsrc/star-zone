package org.flylib.mall.shop.controller.mobile;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.shop.entity.Topic;
import org.flylib.mall.shop.service.TopicService;
import org.flylib.mall.shop.util.ObjectConverter;
import org.flylib.mall.shop.vo.TopicMobileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/mobile/topic")
@RestController
    public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("test")
    public String test() {
        topicService.test();
        return "/mobile/topic--testDone";
    }

    @RequestMapping("byPageVisible")
    public String byPage(int page, int limit) {
        List<TopicMobileVO> voList = topicService.byPageVisible(page, limit);
        return JSON.toJSONString(voList);
    }

    @RequestMapping("findById")
    public TopicMobileVO findById(long topicId){
        Topic topic = topicService.findById(topicId);
        return ObjectConverter.topic2MVO(topic);
    }
}
