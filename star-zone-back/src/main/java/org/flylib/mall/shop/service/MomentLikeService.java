package org.flylib.mall.shop.service;

import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.shop.entity.MomentLike;
import org.flylib.mall.shop.repository.MomentLikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MomentLikeService {
    private static final Logger log = LoggerFactory.getLogger(MomentLikeService.class);

    @Resource
    private MomentLikeRepository momentLikeRepository;
    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    public void like(long momentId, long userId) {
        MomentLike exist = momentLikeRepository.findExist(momentId, userId);
        if (exist != null && exist.getId() >0) {
            momentLikeRepository.delete(momentId, exist.getId());
            log.info("momentLikeRepository_delete_exed");
        } else {
            MomentLike momentLike = new MomentLike();
            momentLike.setId(snowflakeIdWorker.nextId());
            momentLike.setMomentId(momentId);
            momentLike.setLikeUserId(userId);
            momentLike.setCreateTime(new Date());
            momentLikeRepository.add(momentLike);
            log.info("momentLikeRepository_add_exed");
        }
    }
}
