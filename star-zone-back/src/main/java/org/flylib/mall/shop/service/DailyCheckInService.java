package org.flylib.mall.shop.service;

import org.apache.commons.lang3.time.DateUtils;
import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.shop.entity.DailyCheckIn;
import org.flylib.mall.shop.repository.DailyCheckInRepository;
import org.flylib.mall.shop.repository.UserScoreRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class DailyCheckInService {

    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    @Resource
    private DailyCheckInRepository dailyCheckInRepository;

    @Resource
    private UserScoreService userScoreService;

    /**
     *
     * @param userId
     * @return 返回0:签到成功; 返回1: 已签到; 返回2:签到失败
     */
    public int checkIn(long userId) {
        boolean checked = isChecked(userId);
        if (checked) {
            return 1;
        }

        DailyCheckIn dailyCheckIn=new DailyCheckIn();
        dailyCheckIn.setId(snowflakeIdWorker.nextId());
        dailyCheckIn.setUserId(userId);
        dailyCheckIn.setCreateTime(new Date());
        int updatedCount =0;
        try {
             updatedCount = dailyCheckInRepository.add(dailyCheckIn);
        } catch (Exception e) {
            updatedCount = 0;
        }

        if (updatedCount>0) {
            userScoreService.updateScoreAfterCheckIn(userId);
            return 0;
        } else {
            return 2;
        }
    }

    public boolean isChecked(long userId){
        boolean checked = false;
        Date date = dailyCheckInRepository.getLatestTime(userId);

        if (date!=null) {
            checked = DateUtils.isSameDay(new Date(), date);
        }
        return checked;
    }
}
