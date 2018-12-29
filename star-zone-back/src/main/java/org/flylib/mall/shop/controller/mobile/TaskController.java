package org.flylib.mall.shop.controller.mobile;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.constant.ResponseMsg;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.shop.service.DailyCheckInService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mobile/task")
public class TaskController {

    @Resource
    private DailyCheckInService dailyCheckInService;

    @RequestMapping("/checkIn")
    public String checkIn(HttpServletRequest request) {
        String userIdStr = request.getHeader("userId");
        if (StringUtils.isEmpty(userIdStr)) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(1);
            responseData.setMsg("FAILED, userIdStr is empty");
            responseData.setData(null);
            return JSON.toJSONString(responseData);
        } else {
            long userId = 0;
            try {
                userId = Long.valueOf(userIdStr);
            } catch (Exception e) {
                ResponseData responseData1 = new ResponseData();
                responseData1.setCode(2);
                responseData1.setMsg("FAILED, userIdStr is not number");
                responseData1.setData(null);
                return JSON.toJSONString(responseData1);
            }

            int checkInResult = dailyCheckInService.checkIn(userId);
            ResponseData responseData = new ResponseData();
            responseData.setCode(ResponseCode.OK);
            responseData.setMsg(ResponseMsg.OK);
            responseData.setData(checkInResult + "");
            return JSON.toJSONString(responseData);
        }
    }

    @RequestMapping("/isChecked")
    public String isChecked(HttpServletRequest request) {
        String userIdStr = request.getHeader("userId");
        if (StringUtils.isEmpty(userIdStr)) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(1);
            responseData.setMsg("FAILED, userIdStr is empty");
            responseData.setData(null);
            return JSON.toJSONString(responseData);
        } else {
            long userId = 0;
            try {
                userId = Long.valueOf(userIdStr);
            } catch (Exception e) {
                ResponseData responseData1 = new ResponseData();
                responseData1.setCode(2);
                responseData1.setMsg("FAILED, userIdStr is not number");
                responseData1.setData(null);
                return JSON.toJSONString(responseData1);
            }

            boolean checked = dailyCheckInService.isChecked(userId);
            ResponseData responseData = new ResponseData();
            responseData.setCode(ResponseCode.OK);
            responseData.setMsg(ResponseMsg.OK);
            responseData.setData(checked);
            return JSON.toJSONString(responseData);
        }
    }
}
