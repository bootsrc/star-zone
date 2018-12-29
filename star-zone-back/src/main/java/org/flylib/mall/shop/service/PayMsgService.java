package org.flylib.mall.shop.service;

import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.common.constant.PayType;
import org.flylib.mall.shop.entity.PayMsg;
import org.flylib.mall.shop.repository.PayMsgRepository;
import org.flylib.mall.shop.util.PayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PayMsgService {
    @Autowired
    private PayMsgRepository payMsgRepository;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    public int add(PayMsg payMsg) {


        if ("支付宝通知".equals(payMsg.getTitle())) {
            payMsg.setPayType(PayType.ALIPAY);
            //少明商家号通过扫码向你付款0.01元
            String text = payMsg.getText();
            long amount = PayUtil.getAmount(text);
            if (amount > 0) {
                payMsg.setAmount(amount);
            }
        } else if (payMsg.getTitle() != null && payMsg.getTitle().contains("微信")) {
            payMsg.setPayType(PayType.WXPAY);
        } else {
            return 0;
        }
        payMsg.setMsgId(snowflakeIdWorker.nextId());
        payMsg.setCreateTime(new Date().getTime());
        return payMsgRepository.add(payMsg);
    }


}
