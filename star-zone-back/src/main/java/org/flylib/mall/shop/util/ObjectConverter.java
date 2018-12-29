package org.flylib.mall.shop.util;

import org.flylib.mall.common.util.CommonObjectConverter;
import org.flylib.mall.common.dto.BaseGoodsDTO;
import org.flylib.mall.common.dto.OrderDTO;
import org.flylib.mall.shop.constant.MiscConstant;
import org.flylib.mall.shop.entity.*;
import org.flylib.mall.shop.vo.GoodsVO;
import org.flylib.mall.shop.vo.TopicMobileVO;
import org.flylib.mall.shop.vo.TopicVO;
import org.flylib.mall.shop.vo.mobile.UserInfo;

import java.util.*;

public class ObjectConverter {
    public static OrderDTO order2DTO(Order order) {
        Set<String> exceptGetterSet = new HashSet<String>();
        exceptGetterSet.add("getCreateTime");
        exceptGetterSet.add("getUpdateTime");
        exceptGetterSet.add("getDeliverTime");
        exceptGetterSet.add("getConfirmTime");
        OrderDTO dto = CommonObjectConverter.convert(order, OrderDTO.class, exceptGetterSet);
        dto.setCreateTime(order.getCreateTime().getTime());
        dto.setUpdateTime(order.getUpdateTime().getTime());
        dto.setDeliverTime(order.getDeliverTime().getTime());
        dto.setConfirmTime(order.getConfirmTime().getTime());
        return dto;
    }

    public static Order orderDTO2Entity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setUserId(dto.getUserId());
        order.setAmount(dto.getAmount());
        order.setCreateTime(new Date(dto.getCreateTime()));
        return order;
    }

    public static BaseGoods baseGoodsDTO2Entity(BaseGoodsDTO dto) {
        BaseGoods baseGoods = new BaseGoods();
        baseGoods.setId(dto.getId());
        baseGoods.setCatalogId(dto.getCatalogId());
        baseGoods.setName(dto.getName());
        baseGoods.setImg(dto.getImg());
        baseGoods.setLabel(dto.getLabel());
        baseGoods.setPrice(dto.getPrice());
        baseGoods.setOriginPrice(dto.getOriginPrice());
        baseGoods.setHot(dto.isHot());
        baseGoods.setStatus(dto.getStatus());
        baseGoods.setSortValue(dto.getSortValue());
        return baseGoods;
    }

    public static BaseGoodsDTO baseGoods2DTO(BaseGoods baseGoods) {
        BaseGoodsDTO dto = new BaseGoodsDTO();
        dto.setId(baseGoods.getId());
        dto.setCatalogId(baseGoods.getCatalogId());
        dto.setName(baseGoods.getName());
        dto.setImg(baseGoods.getImg());
        dto.setLabel(baseGoods.getLabel());
        dto.setPrice(baseGoods.getPrice());
        dto.setOriginPrice(baseGoods.getOriginPrice());
        dto.setHot(baseGoods.isHot());
        dto.setStatus(baseGoods.getStatus());
        dto.setSortValue(baseGoods.getSortValue());
        return dto;
    }

//    public static <T1, T2> T2 convert(T1 src, Class<T2> clazz) {
//        Class srcClazz = src.getClass();
//        T2 target = null;
//        try {
//            target = clazz.getDeclaredConstructor().newInstance();
//            Map<String, Object> srcFieldMap = new HashMap<String, Object>();
//            for (Field field : srcClazz.getDeclaredFields()){
//                field.setAccessible(true);
//                srcFieldMap.put(field.getName(), field.get(src));
//            }
//
//            for (Field field : clazz.getDeclaredFields()){
//                field.setAccessible(true);
//                String fieldName = field.getName();
//                if (! "serialVersionUID".equals(fieldName)) {
//                    field.set(target, srcFieldMap.get(fieldName));
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return target;
//    }

//    public static void main(String[] args){
//        BaseGoodsDTO dto=new BaseGoodsDTO();
//        dto.setCatalogId(1);
//        dto.setName("SuitsA");
//        dto.setImg("/aaa/bbb.jpg");
//        dto.setLabel("Sale");
//        dto.setPrice(9900);
//        dto.setOriginPrice(10000);
//        dto.setHot(true);
//        dto.setStatus(1);
//
//        BaseGoods tartet = convert(dto, BaseGoods.class);
//        System.out.println(tartet);
//    }

    @Deprecated
    public static Goods baseGoods2Goods(BaseGoods baseGoods) {
        Goods goods=new Goods();
        goods.setId(baseGoods.getId());
        goods.setCatalogId(baseGoods.getCatalogId());
        goods.setName(baseGoods.getName());
        goods.setImg(baseGoods.getImg());
        goods.setLabel(baseGoods.getLabel());
        goods.setPrice(baseGoods.getPrice());
        goods.setOriginPrice(baseGoods.getOriginPrice());
        goods.setHot(baseGoods.isHot());
        goods.setStatus(baseGoods.getStatus());
        goods.setSortValue(baseGoods.getSortValue());
        return goods;
    }

    /**
     * js端使用的VO
     * @param topic
     * @return
     */
    public static TopicVO topic2VO(Topic topic) {
        TopicVO topicVO = new TopicVO();
        topicVO.setTopicId(topic.getTopicId() + "");
        topicVO.setTitle(topic.getTitle());
        topicVO.setImg(topic.getImg());
        topicVO.setIntroduction(topic.getIntroduction());
        topicVO.setCreateTime(topic.getCreateTime());
        topicVO.setUpdateTime(topic.getUpdateTime());
        topicVO.setContent(topic.getContent());
        topicVO.setStatus(topic.getStatus());
        topicVO.setSerial(topic.getSerial());
        return topicVO;
    }

    /**
     * 安卓/iOS端使用的VO
     * @param topic
     * @return
     */
    public static TopicMobileVO topic2MVO(Topic topic) {
        TopicMobileVO topicMobileVO = new TopicMobileVO();
        topicMobileVO.setTopicId(topic.getTopicId());
        topicMobileVO.setTitle(topic.getTitle());
        topicMobileVO.setImg(topic.getImg());
        topicMobileVO.setIntroduction(topic.getIntroduction());
        if (topic.getCreateTime() != null) {
            topicMobileVO.setCreateTime(topic.getCreateTime().getTime());
        }
        if (topic.getUpdateTime() != null) {
            topicMobileVO.setUpdateTime(topic.getUpdateTime().getTime());
        }
        topicMobileVO.setContent(topic.getContent());
        topicMobileVO.setStatus(topic.getStatus());
        return topicMobileVO;
    }

    public static Topic topicVO2Obj(TopicVO topicVO) {
        Topic topic = new Topic();
        topic.setTopicId(Long.valueOf(topicVO.getTopicId()));
        topic.setTitle(topicVO.getTitle());
        topic.setImg(topicVO.getImg());
        topic.setIntroduction(topicVO.getIntroduction());
        topic.setCreateTime(topicVO.getCreateTime());
        topic.setUpdateTime(topicVO.getUpdateTime());
        topic.setContent(topicVO.getContent());
        topic.setStatus(topicVO.getStatus());
        topic.setSerial(topicVO.getSerial());
        return topic;
    }

    public static Goods goodsVO2Obj(GoodsVO goodsVO){
        Goods goods=new Goods();
        goods.setId(Long.valueOf(goodsVO.getId()));
        goods.setCatalogId(goodsVO.getCatalogId());
        goods.setName(goodsVO.getName());
        goods.setImg(goodsVO.getImg());
        goods.setLabel(goodsVO.getLabel());
        goods.setPrice(goodsVO.getPrice());
        goods.setOriginPrice(goodsVO.getOriginPrice());
        goods.setHot(goodsVO.isHot());
        goods.setStatus(goodsVO.getStatus());
        goods.setSortValue(goodsVO.getSortValue());
        goods.setStock(goodsVO.getStock());
        // 扩展字段
        goods.setVersion(goodsVO.getVersion());
        goods.setIntroduce(goodsVO.getIntroduce());
        goods.setProductHtml(goodsVO.getProductHtml());
        goods.setBigImg(goodsVO.getBigImg());
        goods.setImgList(goodsVO.getImgList());
        goods.setCreateTime(goodsVO.getCreateTime());
        goods.setUpdateTime(goodsVO.getUpdateTime());

        return goods;
    }

    public static GoodsVO goods2VO(Goods goods){
        GoodsVO goodsVO=new GoodsVO();
        goodsVO.setId(goods.getId() +"");
        goodsVO.setCatalogId(goods.getCatalogId());
        goodsVO.setName(goods.getName());
        goodsVO.setImg(goods.getImg());
        goodsVO.setLabel(goods.getLabel());
        goodsVO.setPrice(goods.getPrice());
        goodsVO.setOriginPrice(goods.getOriginPrice());
        goodsVO.setHot(goods.isHot());
        goodsVO.setStatus(goods.getStatus());
        goodsVO.setSortValue(goods.getSortValue());
        goodsVO.setStock(goods.getStock());
        // 扩展字段
        goodsVO.setVersion(goods.getVersion());
        goodsVO.setIntroduce(goods.getIntroduce());
        goodsVO.setProductHtml(goods.getProductHtml());
        goodsVO.setBigImg(goods.getBigImg());
        goodsVO.setImgList(goods.getImgList());
        goodsVO.setCreateTime(goods.getCreateTime());
        goodsVO.setUpdateTime(goods.getUpdateTime());

        return goodsVO;
    }

    public static UserInfo userProfile2UserInfo(UserProfile userProfile) {
        if (userProfile==null || userProfile.getUserId() < 1) {
            return null;
        }
        UserInfo userInfo=new UserInfo();
        userInfo.setId(userProfile.getUserId());
        userInfo.setCover(MiscConstant.COVER_DEFAULT);
        userInfo.setHeadImg(userProfile.getHeadImg());
        userInfo.setNickname(userProfile.getNickname());
        userInfo.setUsername(userProfile.getNickname());
        return userInfo;
    }
}
