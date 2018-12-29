package org.flylib.mall.shop.service;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.common.constant.UserProfileConstant;
import org.flylib.mall.shop.constant.DefaultUserProfile;
import org.flylib.mall.shop.constant.MiscConstant;
import org.flylib.mall.shop.entity.User;
import org.flylib.mall.shop.entity.UserProfile;
import org.flylib.mall.shop.entity.UserScore;
import org.flylib.mall.shop.model.PageParam;
import org.flylib.mall.shop.model.UserProfileDetail;
import org.flylib.mall.shop.repository.impl.UserProfileRepository;
import org.flylib.mall.shop.util.CellPhoneNumberUtil;
import org.flylib.mall.shop.util.ObjectConverter;
import org.flylib.mall.shop.vo.mobile.MomentInfo;
import org.flylib.mall.shop.vo.mobile.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileService {
    private static final Logger log = LoggerFactory.getLogger(UserProfileService.class);

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserService userService;

    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    @Resource
    private UserScoreService userScoreService;

    public UserProfile saveProfile(UserProfile userProfile) {
        /**
         * responseCode = 1;系统错误
         */
        int responseCode = 1;
        if (userProfile != null && userProfile.getUserId() > 0) {
            long userId = userProfile.getUserId();
            String newHeadImg = userProfile.getHeadImg();
            long newHeadVersion = userProfile.getHeadVersion();

            if (StringUtils.isEmpty(newHeadImg)) {
                newHeadImg = DefaultUserProfile.HEAD_IMG;
                userProfile.setHeadImg(newHeadImg);
            }
            if (newHeadVersion < 1) {
                newHeadVersion = snowflakeIdWorker.nextId();
                userProfile.setHeadVersion(newHeadVersion);
            }
            UserProfile existProfile = userProfileRepository.findById(userId);

            if (existProfile == null) {
                log.info("Inserting UserProfile...");
                int updatedCount = userProfileRepository.add(userProfile);
                if (updatedCount == 1) {
                    responseCode = 0;
                    return userProfile;
                }
            } else {
                log.info("Updating UserProfile...");
                String existHeadImg = existProfile.getHeadImg();
                long existHeadVersion = existProfile.getHeadVersion();

                if (newHeadImg.equals(existHeadImg)) {
                    if (existHeadVersion > 0) {
                        userProfile.setHeadVersion(existHeadVersion);
                    } else {
                        userProfile.setHeadVersion(newHeadVersion);
                    }
                } else {
                    userProfile.setHeadVersion(newHeadVersion);
                }

                int updatedCount = userProfileRepository.update(userProfile);
                if (updatedCount == 1 || updatedCount == 0) {
                    responseCode = 0;
                    return userProfile;
                }
            }
        }
        return null;
    }

    public UserProfile findById(long userId) {
        UserProfile userProfile = userProfileRepository.findById(userId);
        return userProfile;
    }

    public UserProfileDetail getUserProfileDetail(long userId) {
        UserProfileDetail detail = null;
        UserProfile userProfile = findById(userId);
        if (userProfile != null) {
            detail = new UserProfileDetail();
            detail.setUserId(userProfile.getUserId());
            detail.setMobile(userProfile.getMobile());
            detail.setSex(userProfile.getSex());
            detail.setStarSign(userProfile.getStarSign());
            detail.setAge(userProfile.getAge());
            detail.setNickname(userProfile.getNickname());
            detail.setHeadImg(userProfile.getHeadImg());
            detail.setHeadVersion(userProfile.getHeadVersion());

            UserScore userScore = userScoreService.getScore(userId);
            detail.setScore(userScore.getScore());
            detail.setCheckInCount(userScore.getCheckInCount());
        }
        return detail;
    }

    public UserProfileDetail getUserProfileDetailNotEmpty(long userId) {
        UserProfileDetail detail = null;
        UserProfile userProfile = findByIdNotEmpty(userId);
        if (userProfile != null) {
            detail = new UserProfileDetail();
            detail.setUserId(userProfile.getUserId());

            String hiedCellPhoneNumber = CellPhoneNumberUtil.hide(userProfile.getMobile());
            detail.setMobile(hiedCellPhoneNumber);
            detail.setSex(userProfile.getSex());
            detail.setStarSign(userProfile.getStarSign());
            detail.setAge(userProfile.getAge());
            detail.setNickname(userProfile.getNickname());
            detail.setHeadImg(userProfile.getHeadImg());
            detail.setHeadVersion(userProfile.getHeadVersion());

            UserScore userScore = userScoreService.getScore(userId);
            detail.setScore(userScore.getScore());
            detail.setCheckInCount(userScore.getCheckInCount());
        }
        return detail;
    }

    public UserProfile findByIdNotEmpty(long userId) {
        UserProfile exist = findById(userId);
        if (exist == null) {
            UserProfile newUserProfile = new UserProfile();
            User user = userService.findById(userId);
            if (user == null) {
                return null;
            } else {
                newUserProfile.setUserId(user.getId());
                newUserProfile.setHeadVersion(0);
                newUserProfile.setHeadImg(DefaultUserProfile.HEAD_IMG);
                String nickname = "手机" + user.getMobile().substring(7);

                newUserProfile.setNickname(nickname);
                newUserProfile.setAge(0);
                newUserProfile.setStarSign(0);
                newUserProfile.setSex(0);
                newUserProfile.setMobile(user.getMobile());
                return newUserProfile;
            }
        } else {
            return exist;
        }
    }

    public UserInfo getUserInfoById(long userId) {
        UserProfile userProfile = findById(userId);
        UserInfo userInfo = ObjectConverter.userProfile2UserInfo(userProfile);
        return userInfo;
    }

    public int test() {
//        long userId = 452958329837191168L;
//        UserProfile userProfile = new UserProfile();
//        userProfile.setUserId(userId);
//        User user = userService.findById(userId);
//
//        userProfile.setMobile(user.getMobile());
//        userProfile.setSex(UserProfileConstant.SEX_MALE);
//        //处女座
//        userProfile.setStarSign(5);
//        userProfile.setAge(10);
//        int responseCode = saveProfile(userProfile);
//        log.info("---responseCode={}", responseCode);
//        return responseCode;
        return 0;
    }

    public UserInfo newAnonymousUserInfo(long userId) {
        UserInfo userInfo = new UserInfo();
        User user = userService.findById(userId);
        String mobile = user.getMobile();
        String username = "手机" + mobile.substring(7);
        userInfo.setUsername(username);
        userInfo.setNickname(username);
        userInfo.setHeadImg(DefaultUserProfile.HEAD_IMG);
        userInfo.setCover(MiscConstant.COVER_DEFAULT);
        userInfo.setId(userId);
        return userInfo;
    }

    public List<UserProfile> byPageForMobile(int page, int limit, long userId) {
        int pageValue = page > 0 ? page : 0;
        limit = limit > 0 ? limit : 10;
        PageParam pageParam = new PageParam();
        pageParam.setPage(pageValue);
        pageParam.setLimit(limit);
        List<UserProfile> userProfileList = userProfileRepository.byPageForMobile(pageParam);
        return userProfileList;
    }

    public UserProfile addDefaultUser(long userId, String mobile) {
        UserProfile defaultProfile = new UserProfile();
        defaultProfile.setUserId(userId);
        defaultProfile.setMobile(mobile);

        defaultProfile.setStarSign(DefaultUserProfile.STAR_SIGN);
        defaultProfile.setAge(DefaultUserProfile.AGE);
        defaultProfile.setSex(DefaultUserProfile.SEX);
        defaultProfile.setHeadImg(DefaultUserProfile.HEAD_IMG);
        defaultProfile.setHeadVersion(DefaultUserProfile.HEAD_VERSION);
        defaultProfile.setNickname(DefaultUserProfile.getDefaultNickname(userId));

        log.info("---addDefaultUser={}", JSON.toJSONString(defaultProfile));
        return saveProfile(defaultProfile);
    }
}
