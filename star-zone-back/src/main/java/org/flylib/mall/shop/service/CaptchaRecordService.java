package org.flylib.mall.shop.service;

import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.shop.constant.SmsConstant;
import org.flylib.mall.shop.entity.CaptchaRecord;
import org.flylib.mall.shop.repository.CaptchaRecordRepository;
import org.flylib.mall.shop.util.CaptchaUtil;
import org.flylib.mall.shop.util.DateUtil;
import org.flylib.mall.shop.util.SmsUtil;
import org.flylib.passport.constant.AuthResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CaptchaRecordService {
    private static final Logger log = LoggerFactory.getLogger(CaptchaRecordService.class);
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private CaptchaRecordRepository captchaRecordRepository;

    public CaptchaRecord add(String mobile, String captcha) {
        CaptchaRecord captchaRecord = new CaptchaRecord();
        long captchaId = snowflakeIdWorker.nextId();
        captchaRecord.setId(captchaId);
        captchaRecord.setMobile(mobile);
        captchaRecord.setCaptcha(captcha);
        Date nowDate = new Date();
        Date expireDate = DateUtil.addByMinute(nowDate, SmsConstant.EXPIRE_MINITUES);
        captchaRecord.setCreateTime(nowDate);
        captchaRecord.setExpireTime(expireDate);

        captchaRecordRepository.add(captchaRecord);
        return captchaRecord;
    }

    public void test() {
        String mobile = "15601623391";
        String captcha = CaptchaUtil.newCaptcha();
        CaptchaRecord captchaRecord = add(mobile, captcha);
        long id = captchaRecord.getId();
        CaptchaRecord storedCaptcha = captchaRecordRepository.findById(id);
        log.info("---storedCaptcha={}", storedCaptcha);
    }

    public ResponseData fetchCaptcha(String mobile) {
        ResponseData responseData = new ResponseData();
        // 限制短信轰炸
        CaptchaRecord exists = captchaRecordRepository.findLatestByMobile(mobile);
        if (exists == null) {
            sendCaptcha(mobile);
            responseData.setCode(AuthResponseCode.SUCCESS);
            responseData.setMsg(AuthResponseCode.SUCCESS_DESC);
        } else {
            if (exists.getExpireTime().getTime() > new Date().getTime()) {
                log.info("-------已经获取过验证码，已存在的情况为,mobile={}," +
                        "captcha={}", mobile, exists.getCaptcha());
                //
                responseData.setCode(AuthResponseCode.USER_CAPTCHA_TOOFAST);
                responseData.setMsg(AuthResponseCode.USER_CAPTCHA_TOOFAST_DESC);
            } else {
                // 过期了。可以获取验证码
                sendCaptcha(mobile);
                responseData.setCode(AuthResponseCode.SUCCESS);
                responseData.setMsg(AuthResponseCode.SUCCESS_DESC);
            }
        }
        return responseData;
    }

    private void sendCaptcha(String mobile) {
        String captcha = CaptchaUtil.newCaptcha();
        CaptchaRecord captchaRecord = add(mobile, captcha);
        log.info("----fetchCaptcha_result={}", captcha);
        SmsUtil.sendCaptcha(mobile, captcha);
    }

    public CaptchaRecord findLatestByMobile(String mobile) {
        return captchaRecordRepository.findLatestByMobile(mobile);
    }
}
