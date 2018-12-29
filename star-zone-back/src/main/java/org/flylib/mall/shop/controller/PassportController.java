package org.flylib.mall.shop.controller;

import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.shop.constant.AccountType;
import org.flylib.mall.shop.service.PassportService;
import org.flylib.passport.entity.LoginResult;
import org.flylib.passport.entity.Passport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/passport")
@RestController
public class PassportController {
    private static final Logger log = LoggerFactory.getLogger(PassportController.class);

    @Autowired
    private PassportService passportService;

    @RequestMapping("register")
    public LoginResult register(String mobile, String captcha, String password){
        LoginResult loginResult= passportService.register(mobile, captcha, password);
        return loginResult;
    }

    @RequestMapping("login")
    public LoginResult login(String mobile, String password){
        LoginResult loginResult = passportService.login(mobile, password, AccountType.MOBILE);
        log.info("----login-----");
        return loginResult;
    }

    @RequestMapping("changePassword")
    public LoginResult changePassword(String mobile, String captcha, String password){
        LoginResult loginResult= passportService.changePassword(mobile, captcha, password);
        return loginResult;
    }
}
