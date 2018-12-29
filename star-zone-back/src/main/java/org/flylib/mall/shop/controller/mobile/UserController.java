package org.flylib.mall.shop.controller.mobile;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.constant.ResponseMsg;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.shop.entity.UserProfile;
import org.flylib.mall.shop.model.UserProfileDetail;
import org.flylib.mall.shop.service.UserProfileService;
import org.flylib.mall.shop.service.UserService;
import org.flylib.mall.shop.util.CellPhoneNumberUtil;
import org.flylib.passport.annotation.AuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@AuthController
@RequestMapping("/mobile/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping("demo")
    public ResponseData demo() {
        ResponseData responseData = ResponseData.newOK();
        responseData.setData("Demo Data for it.");
        return responseData;
    }

    @RequestMapping("test")
    public ResponseData test() {
        int resonseCode = userProfileService.test();
        ResponseData responseData = new ResponseData();
        responseData.setCode(resonseCode);
        responseData.setData("Test Data for it.");
        return responseData;
    }

    //    @RequestMapping(value = "saveProfile", consumes = "application/json;charset=UTF-8")
    @RequestMapping("saveProfile")
    public UserProfile saveProfile(String userProfile) {
        if (!StringUtils.isEmpty(userProfile)) {
            UserProfile userProfile1 = JSON.parseObject(userProfile, UserProfile.class);
            UserProfile resultProfile = userProfileService.saveProfile(userProfile1);
            return resultProfile;
        }
        return null;
    }

    @RequestMapping("getProfile")
    public UserProfile getProfile(HttpServletRequest request) {
        String userIdStr = request.getHeader("userId");
        return userProfileService.findById(Long.valueOf(userIdStr));
    }

    @RequestMapping("getProfileDetail")
    public String getUserProfileDetail(HttpServletRequest request) {
        String userIdStr = request.getHeader("userId");
        UserProfileDetail detail = userProfileService.getUserProfileDetail(Long.valueOf(userIdStr));
        return JSON.toJSONString(detail);
    }

    @RequestMapping("otherProfile")
    public String otherProfile(HttpServletRequest request, long otherUserId) {
        UserProfile userProfile = userProfileService.findByIdNotEmpty(otherUserId);
        String hiedCellPhoneNumber = CellPhoneNumberUtil.hide(userProfile.getMobile());
        userProfile.setMobile(hiedCellPhoneNumber);
        return JSON.toJSONString(userProfile);
    }

    @RequestMapping("otherProfileDetail")
    public String otherProfileDetail(HttpServletRequest request, long otherUserId) {
        UserProfileDetail detail = userProfileService.getUserProfileDetailNotEmpty(otherUserId);
        return JSON.toJSONString(detail);
    }
}
