package org.flylib.mall.shop.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.constant.ResponseMsg;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.shop.constant.MiscConstant;
import org.flylib.mall.shop.entity.Comment;
import org.flylib.mall.shop.entity.Moment;
import org.flylib.mall.shop.entity.MomentLike;
import org.flylib.mall.shop.entity.UserProfile;
import org.flylib.mall.shop.model.PageParam;
import org.flylib.mall.shop.repository.CommentRepository;
import org.flylib.mall.shop.repository.MomentLikeRepository;
import org.flylib.mall.shop.repository.MomentRepository;
import org.flylib.mall.shop.vo.MomentMobileVO;
import org.flylib.mall.shop.vo.mobile.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MomentService {
    private static final Logger log = LoggerFactory.getLogger(MomentService.class);
    @Resource
    private MomentRepository momentRepository;

    @Resource
    private CommentRepository commentRepository;

    @Resource
    private UserProfileService userProfileService;

    @Resource
    private MomentLikeRepository momentLikeRepository;

    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    public ResponseData add(Moment moment) {
        ResponseData responseData = new ResponseData();
        int responseCode = 2;
        String responseMsg = "系统错误";

        int updatedCount = momentRepository.add(moment);
        if (updatedCount == 1) {
            responseCode = ResponseCode.OK;
            responseMsg = ResponseMsg.OK;
        }
        responseData.setCode(responseCode);
        responseData.setMsg(responseMsg);
        return responseData;
    }

    public List<MomentInfo> byPageForMobile(int page, int limit, long userId) {
        List<MomentInfo> momentInfoList = new ArrayList<MomentInfo>();

        int pageValue = page > 0 ? page : 0;
        limit = limit > 0 ? limit : 10;
        PageParam pageParam = new PageParam();
        pageParam.setPage(pageValue);
        pageParam.setLimit(limit);

        List<Moment> momentList = momentRepository.byPageForMobile(pageParam);
        List<MomentMobileVO> voList = new ArrayList<MomentMobileVO>();
        if (momentList != null && momentList.size() > 0) {
            for (Moment moment : momentList) {
                int count = commentRepository.countByMomentId(moment.getId());
                long momentUserId = moment.getUserId();
                UserProfile userProfile = userProfileService.findById(momentUserId);
                int likeCount = momentLikeRepository.countByMomentId(moment.getId());
                MomentLike momentLike = momentLikeRepository.findExist(moment.getId()
                        , userId);
                boolean liked = momentLike != null && momentLike.getId() > 0;

                //
                MomentInfo momentInfo = new MomentInfo();
                momentInfo.setId(moment.getId());
                UserInfo userInfo = new UserInfo();

                userInfo.setHeadImg(userProfile.getHeadImg());
                userInfo.setCover(MiscConstant.COVER_DEFAULT);
                userInfo.setNickname(userProfile.getNickname());
//                userInfo.setUsername();
                userInfo.setId(userProfile.getUserId());
                momentInfo.setAuthor(userInfo);
                MomentContent content = new MomentContent();
                content.setText(moment.getContent());
                String imgStr = moment.getImg();
                if (StringUtils.isEmpty(imgStr)) {
                    content.setPics(new ArrayList<String>());
                } else {
                    String[] picArray = imgStr.split(",");
                    List<String> pics = Arrays.stream(picArray).collect(Collectors.toList());
                    content.setPics(pics);
                }
                momentInfo.setContent(content);
                momentInfo.setCreateTime(moment.getCreateTime());

                List<MomentLike> momentLikeList = momentLikeRepository.findExistByMomentId(moment.getId());
                List<LikeInfo> likes = new ArrayList<LikeInfo>();
                if (momentLikeList != null && momentLikeList.size() > 0) {
                    for (MomentLike momentLikeObj : momentLikeList) {
                        LikeInfo likeInfoObj = momentLike2LikeInfo(momentLikeObj);
                        if (likeInfoObj != null) {
                            likes.add(likeInfoObj);
                        }
                    }
                }
                momentInfo.setLikeList(likes);
                List<CommentInfo> commentInfoList = listCommentInfoByMomentId(moment.getId()
                        , userId);
                momentInfo.setCommentList(commentInfoList);

                momentInfoList.add(momentInfo);
            }
        }

        return momentInfoList;
    }

    /**
     * 考虑到了个人资料为空的用户，给别人点赞后，显示点赞不报错
     * @param momentLike
     * @return
     */
    private LikeInfo momentLike2LikeInfo(MomentLike momentLike) {
        LikeInfo likeInfo = new LikeInfo();
        likeInfo.setId(momentLike.getId());
        likeInfo.setMomentId(momentLike.getMomentId());
        long likeUserId = momentLike.getLikeUserId();
        UserInfo userInfo = userProfileService.getUserInfoById(likeUserId);
        if (userInfo == null) {
            log.info("---userId={},USER_PROFILE_IS_NULL__should complete his profile---");
            userInfo = userProfileService.newAnonymousUserInfo(likeUserId);
            if (userInfo == null) {
                return null;
            }
        }
        likeInfo.setUserInfo(userInfo);
        return likeInfo;
    }

    public CommentInfo addComment(String commentText, long momentId, long userId) {
        Comment comment = new Comment();
        comment.setId(snowflakeIdWorker.nextId());
        comment.setMomentId(momentId);
        comment.setCommentText(commentText);
        comment.setUserId(userId);
        comment.setCreateTime(new Date().getTime());
        int updatedCount = commentRepository.add(comment);
        if (updatedCount == 1) {
            CommentInfo commentInfo = new CommentInfo();
            commentInfo.setId(comment.getId());
            commentInfo.setDeletable(userId == comment.getUserId());
            commentInfo.setMomentId(momentId);
            commentInfo.setContent(commentText);

            if (comment.getUserId() > 0) {
                UserInfo author = userProfileService.getUserInfoById(userId);
                commentInfo.setAuthor(author);
            }
            return commentInfo;
        } else {
            return null;
        }
    }

    public List<CommentInfo> listCommentInfoByMomentId(long momentId, long userId) {
        List<CommentInfo> commentInfoList = new ArrayList<>();
        List<Comment> commentList = commentRepository.listByMomentId(momentId);
        if (commentList != null && commentList.size() > 0) {
            for (Comment comment : commentList) {
                CommentInfo commentInfo = new CommentInfo();
                if (comment.getUserId() > 0) {
                    UserInfo author = userProfileService.getUserInfoById(comment.getUserId());
                    commentInfo.setAuthor(author);
                }
                commentInfo.setContent(comment.getCommentText());
                commentInfo.setId(comment.getId());
                commentInfo.setMomentId(comment.getMomentId());
                commentInfo.setDeletable(userId == comment.getUserId());
                commentInfo.setReply(null);

                commentInfoList.add(commentInfo);
            }
        }

        return commentInfoList;
    }

    public int delete(long momentId, long userId) {
        int deletedCommentCount = commentRepository.deleteAll(momentId);
        log.info("deletedCommentCount={}", deletedCommentCount);
        int updatedCount = momentRepository.delete(momentId, userId);
        return updatedCount;
    }

    public int deleteOneComment(long commentId, long userId) {
        int updatedCount = commentRepository.delete(commentId, userId);
        return updatedCount;
    }
}
