package org.flylib.mall.shop.controller.mobile;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notice")
public class NoticeController {

    public Object getAppVersion() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appVersion", 2);
        return jsonObject;
    }
}
