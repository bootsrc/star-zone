package org.flylib.mall.shop.vo.mobile;

/**
 * Created by liushaoming on 2017/11/17.
 */

public interface IComment<T> {

    /**评论创建者*/
    String getCommentCreatorName();
    /**评论回复人*/
    String getReplyerName();
    /**评论内容*/
    String getCommentContent();

    T getData();

}
