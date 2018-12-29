package org.flylib.mall.shop.controller.mobile;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.shop.entity.UserProfile;
import org.flylib.mall.shop.service.UserProfileService;
import org.flylib.mall.shop.vo.mobile.MomentInfo;
import org.flylib.passport.annotation.AuthController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AuthController
@RestController
@RequestMapping("/mobile/friend")
public class FriendController {
    @Resource
    private UserProfileService userProfileService;

    @RequestMapping("/byPage")
    public String byPage(HttpServletRequest request
            , int page, int limit) {
        String userIdStr = request.getHeader("userId");
        if (StringUtils.isEmpty(userIdStr)) {
            return null;
        }
        long userId = Long.valueOf(userIdStr);
        List<UserProfile> profileList = userProfileService.byPageForMobile(page, limit
                , userId);

        String jsonStr = JSON.toJSONString(profileList);
        return jsonStr;
    }


}
