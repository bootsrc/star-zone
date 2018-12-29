package org.flylib.mall.shop.controller.mobile;

import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.shop.service.PushService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mobile/push")
public class MobilePushController {

    @Resource
    private PushService pushService;

    @RequestMapping("/bindRegid")
    public ResponseData bindRegid(HttpServletRequest request, String miRegid) {
        String userIdStr = request.getHeader("userId");
        if (!StringUtils.isEmpty(userIdStr) && Long.valueOf(userIdStr) > 0) {
            pushService.bindRegid(Long.valueOf(userIdStr), miRegid);
            return ResponseData.newOK();
        } else {
            ResponseData responseData = new ResponseData();
            responseData.setCode(1);
            responseData.setMsg("userId不合法");
            return responseData;
        }

    }
}
