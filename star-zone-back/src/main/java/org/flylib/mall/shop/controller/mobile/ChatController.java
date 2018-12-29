package org.flylib.mall.shop.controller.mobile;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.shop.entity.ChatMsg;
import org.flylib.mall.shop.model.ChatUser;
import org.flylib.mall.shop.model.UserMsg;
import org.flylib.mall.shop.service.ChatMsgService;
import org.flylib.passport.annotation.AuthController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@AuthController
@RestController
@RequestMapping("/mobile/chat")
public class ChatController {

    @Resource
    private ChatMsgService chatMsgService;

    @RequestMapping("/send")
    public void send(HttpServletRequest request, long receiverId, String msgBody,
                     int msgType) {

        String userIdStr = request.getHeader("userId");
        if (StringUtils.isEmpty(userIdStr)) {
            return;
        }
        long senderId = Long.valueOf(userIdStr);
        if (senderId > 0) {
            chatMsgService.send(senderId, receiverId, msgBody, msgType);
        }
    }

    @RequestMapping("/msgList")
    public String msgList(HttpServletRequest request, long targetUserId) {
        String userIdStr = request.getHeader("userId");
        if (StringUtils.isEmpty(userIdStr)) {
            return null;
        }
        long userId = Long.valueOf(userIdStr);
        List<ChatMsg> list = chatMsgService.msgList(userId, targetUserId);
        return JSON.toJSONString(list);
    }

    @RequestMapping("/getRecentChatUser")
    public String getRecentChatUser(HttpServletRequest request, int page, int limit) {
        String userIdStr = request.getHeader("userId");
        if (StringUtils.isEmpty(userIdStr)) {
            return null;
        }
        long userId = Long.valueOf(userIdStr);
        List<UserMsg> list = chatMsgService.getRecentChatUser(userId, page, limit);
        return JSON.toJSONString(list);
    }
}
