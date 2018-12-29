package org.flylib.mall.shop.controller;

import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.shop.entity.Topic;
import org.flylib.mall.shop.service.CaptchaRecordService;
import org.flylib.passport.constant.AuthResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("captcha")
@RestController
public class CaptchaController {
    @Autowired
    private CaptchaRecordService captchaRecordService;

    @RequestMapping("test")
    public ResponseData test() {
        captchaRecordService.test();
        ResponseData responseData = ResponseData.newOK();
        return responseData;
    }

    @RequestMapping("fetchCaptcha")
    public ResponseData fetchCaptcha(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            ResponseData responseData =new ResponseData();
            responseData.setCode(AuthResponseCode.USER_MOBILE_IS_EMPTY);
            responseData.setMsg(AuthResponseCode.USER_MOBILE_IS_EMPTY_DESC);
            return responseData;
        }
        ResponseData responseData = captchaRecordService.fetchCaptcha(mobile);
        return responseData;
    }
}
