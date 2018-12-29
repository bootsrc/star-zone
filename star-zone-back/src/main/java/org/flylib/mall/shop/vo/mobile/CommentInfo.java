package org.flylib.mall.shop.vo.mobile;

/**
 * Created by liushaoming on 2016/10/27.
 * <p>
 * 评论
 */

public class CommentInfo implements IComment<CommentInfo> {

    private long id;
    private long momentId;
    private String content;
    private UserInfo author;
    private UserInfo reply;
    private boolean deletable;

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
        this.momentId = momentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    public UserInfo getReply() {
        return reply;
    }

    public void setReply(UserInfo reply) {
        this.reply = reply;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    @Override
    public String getCommentCreatorName() {
        return author == null ? "" : author.getNickname();
    }

    @Override
    public String getReplyerName() {
        return reply == null ? "" : reply.getNickname();
    }

    @Override
    public String getCommentContent() {
        return content;
    }

    @Override
    public CommentInfo getData() {
        return this;
    }


    @Override
    public String toString() {
        return "CommentInfo{" +
                "momentId=" + momentId +
                ", content='" + content + '\'' +
                ", author=" + author.toString() + '\n' +
                ", reply=" + (reply == null ? "null" : reply.toString()) + '\n' +
                '}';
    }
}
