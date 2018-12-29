package org.flylib.mall.shop.service;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.shop.entity.AccountData;
import org.flylib.mall.shop.entity.ChatMsg;
import org.flylib.mall.shop.entity.UserProfile;
import org.flylib.mall.shop.model.ChatUser;
import org.flylib.mall.shop.model.UserMsg;
import org.flylib.mall.shop.repository.ChatMsgRepository;
import org.flylib.mall.shop.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ChatMsgService {
    private static final Logger log = LoggerFactory.getLogger(ChatMsgService.class);

    @Resource
    private ChatMsgRepository chatMsgRepository;
    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;
    @Resource
    private PushService pushService;

    @Resource
    private UserProfileService userProfileService;
    @Resource
    private AccountDataService accountDataService;

    public void send(long senderId, long receiverId, String msgBody,
                     int msgType) {
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setId(snowflakeIdWorker.nextId());
        chatMsg.setSenderId(senderId);
        chatMsg.setReceiverId(receiverId);
        chatMsg.setMsgBody(msgBody);
        chatMsg.setMsgType(msgType);
        chatMsg.setCreateTime(new Date().getTime());
        UserProfile senderProfile = userProfileService.findByIdNotEmpty(senderId);
        if (senderProfile != null && !StringUtils.isEmpty(senderProfile.getHeadImg())) {
            chatMsg.setSenderHead(senderProfile.getHeadImg());
        }
        chatMsgRepository.add(chatMsg);
        //MiPush
        if (senderProfile != null) {
            String data = JSON.toJSONString(chatMsg);
            push(receiverId, senderProfile.getNickname(), chatMsg.getMsgBody(), data);
        }
    }

    private void push(long receiverId, String senderNickname, String msgBody, String data) {
        String noticeTitle = senderNickname + "发来消息";
        AccountData accountData = accountDataService.findByUserId(receiverId);
        if (accountData != null && !StringUtils.isEmpty(accountData.getMiRegid())) {
            pushService.sendChat(receiverId + "", noticeTitle, msgBody, data);
        }
    }


    /**
     * 内存中排序，比在DB中排序效率更高
     *
     * @param userId
     * @return
     */

    public List<ChatMsg> msgList(long userId, long targetUserId) {
        List<ChatMsg> chatMsgList = chatMsgRepository.msgList(userId, targetUserId);
        //根据CreateTime逆序排列
//        Collections.sort(chatMsgList, new Comparator<ChatMsg>() {
//            @Override
//            public int compare(ChatMsg o1, ChatMsg o2) {
//                return Long.valueOf(o2.getCreateTime() - o1.getCreateTime()).intValue();
//            }
//        });
        return chatMsgList;
    }

    public List<UserMsg> getRecentChatUser(long userId, int page, int limit) {
//        int pageValue = page > 0 ? page : 0;
//        limit = limit > 0 ? limit : 10;
        if (page > 0) {
            return new ArrayList<UserMsg>();
        }

        List<ChatUser> list0 = chatMsgRepository.recentUser0(userId);
        List<ChatUser> list1 = chatMsgRepository.recentUser1(userId);

        Map<Long, Long> map0 = new HashMap<>();
        Map<Long, Long> map1 = new HashMap<>();
        Map<Long, Long> processMap = new HashMap<>();

        if (list0 != null && list0.size() > 0) {
            for (ChatUser item : list0) {
                map0.put(item.getUserId(), item.getCreateTime().getTime());
            }
        }
        if (list1 != null && list1.size() > 0) {
            for (ChatUser item : list1) {
                map1.put(item.getUserId(), item.getCreateTime().getTime());
            }
        }

        if (map0.size() > 0) {
            for (Map.Entry<Long, Long> entry : map0.entrySet()) {
                Long userIdItem = entry.getKey();
                long time0 = entry.getValue();

                long processTime = time0;

                if (map1.containsKey(userIdItem)) {
                    long time1 = map1.get(userIdItem);
                    if (time1 > time0) {
                        processTime = time1;
                    }
                }
                processMap.put(userIdItem, processTime);
            }
        }
        // 处理map1的
        if (map1.size() > 0) {
            for (Map.Entry<Long, Long> entry : map1.entrySet()) {
                Long userIdItem = entry.getKey();
                long time1 = entry.getValue();


                if (!map0.containsKey(userIdItem)) {
                    processMap.put(userIdItem, entry.getValue());
                }
            }
        }

//        log.info("processMap.size()={}", processMap.size());

        List<ChatUser> userList = new ArrayList<ChatUser>();
        if (processMap.size() > 0) {
            // 排序
            for (Map.Entry<Long, Long> item : processMap.entrySet()) {
                ChatUser chatUser = new ChatUser();
                chatUser.setUserId(item.getKey());
                chatUser.setCreateTime(new Date(item.getValue()));
                userList.add(chatUser);

            }
            sortUser(userList);
        }

        List<UserMsg> result = chatUser2Msg(userList, userId);
        return result;
    }

    private void sortUser(List<ChatUser> userList) {
        Collections.sort(userList, new Comparator<ChatUser>() {
            @Override
            public int compare(ChatUser o1, ChatUser o2) {

                if (o1 == null || o2 == null) {
                    return 0;
                }
                Long ret = o2.getCreateTime().getTime() - o1.getCreateTime().getTime();
                return ret.intValue();
            }
        });
    }

    private List<UserMsg> chatUser2Msg(List<ChatUser> userList, long userId) {
        List<UserMsg> result = null;
        if (userList != null && userList.size() > 0) {
            result = new ArrayList<UserMsg>();
            for (ChatUser chatUser : userList) {
                UserMsg msg = new UserMsg();
                msg.setTargetId(chatUser.getUserId());
                msg.setCreateTime(chatUser.getCreateTime().getTime());
                UserProfile userProfile = userProfileService.findByIdNotEmpty(chatUser.getUserId());
                msg.setTargetProfile(userProfile);

                ChatMsg chatMsg = chatMsgRepository.getLatestMsg(userId, chatUser.getUserId(),
                        chatUser.getCreateTime());
                msg.setChatMsg(chatMsg);
                result.add(msg);
            }
        }
        return result;
    }
}
