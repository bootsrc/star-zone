package org.flylib.mall.shop.controller;

import org.flylib.mall.shop.constant.MiscConstant;
import org.flylib.mall.shop.vo.IntroduceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("promote")
public class PromoteController {
    private static final Logger log = LoggerFactory.getLogger(PromoteController.class);

    @RequestMapping("introduce")
    public IntroduceVO introduce(){
        log.info("introduce_access");
        IntroduceVO vo = new IntroduceVO();
        vo.setTextContent(MiscConstant.APP_INTRODUCE);
        vo.setAppDownloadWebsite(MiscConstant.APP_DOWNLOAD_WEBSITE);
        return vo;
    }
}
