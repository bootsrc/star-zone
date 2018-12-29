package org.flylib.mall.shop.controller.mobile;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.shop.entity.Campaign;
import org.flylib.mall.shop.entity.UserScore;
import org.flylib.mall.shop.service.UserScoreService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mobile/misc")
public class MiscController {
    @Resource
    private UserScoreService userScoreService;

    @RequestMapping("/getScore")
    public UserScore getScore(HttpServletRequest request){
        String userIdStr = request.getHeader("userId");
        if (StringUtils.isEmpty(userIdStr)) {
            return null;
        }
        long userId = 0;
        try {
            userId = Long.valueOf(userIdStr);
        } catch (Exception e){
            return null;
        }

        return userScoreService.getScore(userId);
    }

    @RequestMapping("/latestCampaign")
    public String latestCampaign() {
        String text = "今年光棍节(2018/11/11)，将举办星座与爱情专题秋游——上海站\n\n"
                +"举办方：appjishu.com\n" +
                "活动地点：预计在浦东新区\n";
        Campaign campaign=new Campaign();
        campaign.setText(text);
        campaign.setReadCount(286);
        return JSON.toJSONString(campaign);
    }
}
