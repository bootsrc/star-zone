package org.flylib.mall.shop.controller.mobile;

import com.alibaba.fastjson.JSON;
import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.shop.entity.Moment;
import org.flylib.mall.shop.service.MomentLikeService;
import org.flylib.mall.shop.service.MomentService;
import org.flylib.mall.shop.vo.MomentMobileVO;
import org.flylib.mall.shop.vo.mobile.CommentInfo;
import org.flylib.mall.shop.vo.mobile.MomentInfo;
import org.flylib.mall.shop.vo.mobile.MomentParam;
import org.flylib.passport.annotation.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@AuthController
@RestController
@RequestMapping("/mobile/moment")
public class MomentController {
    private static final Logger log = LoggerFactory.getLogger(MomentController.class);
    @Resource
    private MomentService momentService;
    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;
    @Resource
    private MomentLikeService momentLikeService;

//    @PostMapping("/add")
//    public ResponseData add(HttpServletRequest request, String img, String content) {
//        String userIdStr = request.getHeader("userId");
//        Moment moment = new Moment();
//        moment.setId(snowflakeIdWorker.nextId());
//        moment.setContent(content);
//        moment.setImg(img);
//        moment.setCreateTime(new Date().getTime());
//        moment.setLikeCount(0);
//        moment.setUserId(Long.valueOf(userIdStr));
//        return momentService.add(moment);
//    }

    @PostMapping("/add")
    public ResponseData add(HttpServletRequest request, @RequestBody MomentParam momentParam) {
        String userIdStr = request.getHeader("userId");

        if (momentParam == null || StringUtils.isEmpty(momentParam.getContent())) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(1);
            responseData.setMsg("文本内容为空");
            return responseData;
        }
        Moment moment = new Moment();
        moment.setId(snowflakeIdWorker.nextId());
        moment.setContent(momentParam.getContent());
        List<String> strList = momentParam.getImg();
//        String img = JSON.toJSONString(strList);
        String img = "";
        if (strList == null || strList.size() == 0) {
            img = "";
        } else {
            for (int i = 0; i < strList.size(); i++) {
                if (i == strList.size() - 1) {
                    img += strList.get(i);
                } else {
                    img += strList.get(i) + ",";
                }
            }
        }

        log.info("addImg={}", img);
        moment.setImg(img);
        moment.setCreateTime(new Date().getTime());
        moment.setLikeCount(0);
        moment.setUserId(Long.valueOf(userIdStr));
        return momentService.add(moment);
    }

//    @RequestMapping("/byPageForMobile")
//    public List<MomentInfo> byPageForMobile(HttpServletRequest request
//            , int page, int limit) {
//        String userIdStr = request.getHeader("userId");
//        return momentService.byPageForMobile(page, limit
//                , Long.valueOf(userIdStr));
//    }

    @RequestMapping("/byPageForMobile")
    public String byPageForMobile(HttpServletRequest request
            , int page, int limit) {
        String userIdStr = request.getHeader("userId");
        List<MomentInfo> infoList = momentService.byPageForMobile(page, limit
                , Long.valueOf(userIdStr));

        String jsonStr = JSON.toJSONString(infoList);
        log.info("byPageForMobile={}", jsonStr);
        return jsonStr;
    }

    @PostMapping("/delete")
    public ResponseData delete(HttpServletRequest request, long id) {
        String userIdStr = request.getHeader("userId");
        momentService.delete(id, Long.valueOf(userIdStr));
        return ResponseData.newOK();
    }

    @PostMapping("/like")
    public ResponseData like(HttpServletRequest request, long momentId) {
        String userIdStr = request.getHeader("userId");
        log.info("MomentController.like__momentId={},userIdStr={}", new Object[]{momentId, userIdStr});
        momentLikeService.like(momentId, Long.valueOf(userIdStr));
        return ResponseData.newOK();
    }

    @PostMapping("/addComment")
    public String addComment(HttpServletRequest request
            , String commentText, long momentId) {
        String userIdStr = request.getHeader("userId");
        CommentInfo commentInfo = momentService.addComment(commentText, momentId, Long.valueOf(userIdStr));
        return JSON.toJSONString(commentInfo);
    }

    @PostMapping("/deleteOneComment")
    public String deleteOneComment(HttpServletRequest request
            , long commentId) {
        String userIdStr = request.getHeader("userId");
        int updatedCount = momentService.deleteOneComment(commentId,
                Long.valueOf(userIdStr));
        ResponseData responseData = null;
        if (updatedCount == 1) {
            responseData = ResponseData.newOK();
            responseData.setData(commentId);
        } else {
            responseData = new ResponseData();
            responseData.setCode(1);
            responseData.setMsg("删除评论失败");
            responseData.setData(0);
        }
        return JSON.toJSONString(responseData);
    }
}
