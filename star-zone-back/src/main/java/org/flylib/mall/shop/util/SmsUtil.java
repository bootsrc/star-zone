package org.flylib.mall.shop.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.flylib.mall.shop.constant.SmsConstant;
import org.json.JSONException;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class SmsUtil {
//    public static void main(String[] args) {
//        String phoneNumber = "15601623391";
//        String captcha = "991102";
//        String sendResult = sendCaptcha(phoneNumber, captcha);
//        System.out.println("---sendResult=" + sendResult);
//    }

    public static SmsSingleSenderResult doSend(String phoneNumber, int templateId, String[] params
    , String smsSign) {
        try {
            SmsSingleSender sender = new SmsSingleSender(SmsConstant.APPID, SmsConstant.APPKEY);
            SmsSingleSenderResult result = sender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);
            return result;
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return null;
    }

    public static String sendCaptcha(String phoneNumber, String captcha) {
        if (StringUtils.isEmpty(captcha) || StringUtils.isEmpty(phoneNumber)) {
            return null;
        }
        SmsSingleSenderResult smsResult = doSend(phoneNumber, SmsConstant.TEMPLATE_ID
                , new String[] {captcha, SmsConstant.EXPIRE_MINITUES + ""}
        , SmsConstant.SMS_SIGN);
        if (smsResult == null){
            return null;
        } else {
            return smsResult.toString();
        }
    }

}
