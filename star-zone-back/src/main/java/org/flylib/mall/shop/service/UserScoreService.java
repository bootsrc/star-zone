package org.flylib.mall.shop.service;

import org.flylib.mall.shop.constant.ScoreConstant;
import org.flylib.mall.shop.entity.UserScore;
import org.flylib.mall.shop.repository.UserScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserScoreService {
    private static final Logger log = LoggerFactory.getLogger(UserScoreService.class);

    @Resource
    private UserScoreRepository userScoreRepository;

    public void updateScoreAfterCheckIn(long userId) {
        log.info("---StepIntoUpdateScoreAfterCheckIn-->");
        UserScore exist = userScoreRepository.findOne(userId);
        if (exist==null) {
            //add
            UserScore userScore = new UserScore();
            userScore.setUserId(userId);
            userScore.setCheckInCount(1);
            userScore.setScore(ScoreConstant.FACTOR_OF_CHECK_IN_AND_SCORE);
            userScoreRepository.add(userScore);
            log.info("--updateScoreAfterCheckIn_add_done--->");
        } else {
            //update
            exist.setCheckInCount(exist.getCheckInCount()+1);
            exist.setScore(exist.getScore()+ ScoreConstant.FACTOR_OF_CHECK_IN_AND_SCORE);
            userScoreRepository.update(exist);
            log.info("--updateScoreAfterCheckIn_update_done--->");
        }
    }

    /**
     * 当数据库中无签到记录时候，填充checkInCount=0，score=0
     * @param userId
     * @return
     */
    public UserScore getScore(long userId) {
        UserScore userScore = userScoreRepository.findOne(userId);
        if (userScore == null) {
            userScore = new UserScore();
            userScore.setCheckInCount(0);
            userScore.setScore(0);
            userScore.setUserId(userId);
        }
        return userScore;
    }
}
