package org.flylib.mall.shop.service;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.flylib.mall.shop.constant.MiSdkConstant;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class PushService {
    private static final Logger log = LoggerFactory.getLogger(PushService.class);

    @Resource
    private AccountDataService accountDataService;

    Sender sender = new Sender(MiSdkConstant.APP_SECRET_KEY);

    public void test(String regId) {
        Constants.useOfficial();
//        Sender sender = new Sender(MiSdkConstant.APP_SECRET_KEY);
        String messagePayload = "This is a message";
        String title = "推送的中文标题";
        String description = "通知内容";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(MiSdkConstant.MY_PACKAGE_NAME)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        Result result = null;
        try {
            result = sender.send(message, regId, 3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.info("Server response: " + "MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().toString()
                + " Reason: " + result.getReason());
    }

//    public void sendChat(String regId, String title, String description, String data) {
//        Constants.useOfficial();
//        String messagePayload = "This is a message";
//        Message message = new Message.Builder()
//                .title(title)
//                .description(description).payload(messagePayload)
//                .restrictedPackageName(MiSdkConstant.MY_PACKAGE_NAME)
//                .notifyType(1)     // 使用默认提示音提示
//                .extra(MiSdkConstant.EXTRA_KEY_PUSH_TYPE, MiSdkConstant.PUSH_TYPE_CHAT)
//                .extra(MiSdkConstant.EXTRA_KEY_DATA, data)
//                .passThrough(0) //passThrough默认0：通知栏推送，1：透传
//                .build();
//        Result result = null;
//        try {
//            result = sender.send(message, regId, 3);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if (result != null ) {
//            log.info("Server response: "+ "MessageId: " + result.getMessageId()
//                    + " ErrorCode: " + result.getErrorCode().getValue()
//                    + " Reason: " + result.getReason());
//        }
//
//    }

    /**
     * @param alias       alias直接传送应用账号系统中的userId就行了。
     *                    开发者可以为指定用户设置别名，然后给这个别名推送消息，效果等同于给RegId推送消息。
     *                    注: 一个RegId可以被设置多个别名，如果设置的别名已经存在，会覆盖掉之前的别名。
     *                    适合单终端登录系统
     *                    ====
     *                    别名alias是小米推送这边提供的一个个性化设定, 可以为每个设备上的app设置一个别名, 方便开发者管理,
     *                    并能做到精细化推送 alias与regid(设备)一一对应, 主要是为了方便开发者管理而推出, 如果多个设备设置
     *                    同一个alias,
     *                    则只能后设置的设备才会受到消息(如果需要多个设备使用同一名称进行管理, 推荐使用UserAccount消息)；
     * @param title
     * @param description
     * @param data
     */
    public void sendChat(String alias, String title, String description, String data) {
        Constants.useOfficial();
        String messagePayload = "This is a message";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(MiSdkConstant.MY_PACKAGE_NAME)
                .notifyType(1)     // 使用默认提示音提示
                .extra(MiSdkConstant.EXTRA_KEY_PUSH_TYPE, MiSdkConstant.PUSH_TYPE_CHAT)
                .extra(MiSdkConstant.EXTRA_KEY_DATA, data)
                .passThrough(0) //passThrough默认0：通知栏推送，1：透传
                .build();
        Result result = null;
        try {
            log.info("---MiPush_StartToSendToAlias:alias={}", alias);
            result = sender.sendToAlias(message, alias, 3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (result != null) {
            log.info("Server response: " + "MessageId: " + result.getMessageId()
                    + " ErrorCode: " + result.getErrorCode().getValue()
                    + " Reason: " + result.getReason());
        }

    }

    public void bindRegid(long userId, String miRegid) {
        accountDataService.save(userId, miRegid);
    }

    public void sendBroadcast(String title, String description) {
        Constants.useOfficial();
        String messagePayload = "This is a message";

//        String topic ="testTopic";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayload)
                .restrictedPackageName(MiSdkConstant.MY_PACKAGE_NAME)
                .notifyType(1)     // 使用默认提示音提示
                .build();
        try {
            sender.broadcastAll(message, 3); //根据topic, 发送消息到指定一组设备上
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void sendBroadcastForUpgrade() {
        String title = "升级通知";
        String description = "请访问http://appjishu.com 下载最新版，体验更有趣的功能";
        sendBroadcast(title, description);
    }

}
