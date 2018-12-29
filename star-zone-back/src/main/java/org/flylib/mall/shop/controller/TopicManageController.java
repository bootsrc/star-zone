package org.flylib.mall.shop.controller;

import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.entity.Topic;
import org.flylib.mall.shop.model.PageForm;
import org.flylib.mall.shop.service.TopicService;
import org.flylib.mall.shop.util.ObjectConverter;
import org.flylib.mall.shop.vo.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/topicManage")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicManageController {
    @Autowired
    private TopicService topicService;

    @RequestMapping("save")
    public ResponseData save(@RequestBody TopicVO topicVO) {
        Topic topic = ObjectConverter.topicVO2Obj(topicVO);
        return topicService.save(topic);
    }

    @RequestMapping("/getTable")
    public TableData list(@RequestBody PageForm pageForm) {
        return topicService.byPageForTable(pageForm.getPageNo(), pageForm.getPageSize());
    }

    @RequestMapping("/delete")
    public ResponseData delete(@RequestBody TopicVO topicVO) {
        if (topicVO != null && !StringUtils.isEmpty(topicVO.getTopicId())) {
            topicService.delete(Long.valueOf(topicVO.getTopicId()));
        }
        ResponseData responseData = ResponseData.newOK();
        return responseData;
    }
}
