package org.flylib.mall.shop.model;

import org.flylib.mall.shop.entity.ChatMsg;
import org.flylib.mall.shop.entity.UserProfile;

import java.io.Serializable;

public class UserMsg implements Serializable {
    private static final long serialVersionUID = 2782911993038073058L;
    private long createTime;
    private ChatMsg chatMsg;
    private long targetId;
    private UserProfile targetProfile;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public ChatMsg getChatMsg() {
        return chatMsg;
    }

    public void setChatMsg(ChatMsg chatMsg) {
        this.chatMsg = chatMsg;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public UserProfile getTargetProfile() {
        return targetProfile;
    }

    public void setTargetProfile(UserProfile targetProfile) {
        this.targetProfile = targetProfile;
    }
}
