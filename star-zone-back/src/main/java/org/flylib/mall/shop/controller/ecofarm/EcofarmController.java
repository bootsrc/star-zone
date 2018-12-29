package org.flylib.mall.shop.controller.ecofarm;

import org.flylib.mall.shop.service.EcofarmService;
import org.flylib.passport.annotation.AuthController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ecofarm/home")
public class EcofarmController {

    @Resource
    private EcofarmService ecofarmService;

    @RequestMapping("")
    public String getWebViewUrl() {
        return ecofarmService.getWebViewUrl();
    }
}
