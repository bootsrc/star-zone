package org.flylib.mall.shop.service;

import org.flylib.mall.common.constant.ResponseCode;
import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.common.util.CommonObjectConverter;
import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.common.dto.GoodsDTO;
import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.entity.Goods;
import org.flylib.mall.shop.entity.Topic;
import org.flylib.mall.shop.model.PageParam;
import org.flylib.mall.shop.repository.GoodsRepository;
import org.flylib.mall.shop.util.ObjectConverter;
import org.flylib.mall.shop.vo.GoodsVO;
import org.flylib.mall.shop.vo.TopicVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class GoodsService {
    private static final Logger log = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 模拟并发连接
     */
    public void test() {
        // 模拟N个连接
//        int maxActive = 200;
//        ExecutorService threadPool = Executors.newFixedThreadPool(maxActive);
//        long goodsId = 436665195520065536L;
//        for (int i = 0; i < 1500; i++) {
//            int j = i;
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    // 测试安全减库存
////                    goodsRepository.minus(goodsId);
//                    // 测试非安全的减库存
//                    if (j % 2 == 0) {
//                        goodsRepository.minusNotSafe(goodsId, 1);
//                    } else {
//                        goodsRepository.minusNotSafe(goodsId, 9);
//                    }
//
//                }
//            };
//            threadPool.submit(runnable);
//        }

        int maxActive = 200;
        ExecutorService threadPool = Executors.newFixedThreadPool(maxActive);
        long goodsId = 436665195520065536L;
        for (int i = 0; i < 1500; i++) {
            int j = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 测试安全减库存
//                    goodsRepository.minus(goodsId);
                    // 测试非安全的减库存
                    if (j % 2 == 0) {
                        goodsRepository.minus(goodsId, 1);
                    } else {
                        goodsRepository.minus(goodsId, 9);
                    }

                }
            };
            threadPool.submit(runnable);
        }
        System.out.println("-----Done---");
    }

    @Transactional
    public void test1() {
        long goodsId = 436665195520065536L;
        goodsRepository.minus(goodsId, 1);
    }

    public ResponseData save(Goods goods){
        if (goods == null) {
            ResponseData responseData = new ResponseData();
            responseData.setCode(1);
            responseData.setMsg("Goods为空");
            return responseData;
        } else {
            if (goods.getId() < 1) {
                long newId = snowflakeIdWorker.nextId();
                goods.setId(newId);
            }
            ResponseData responseData = doSave(goods);
            return responseData;
        }
    }

    private ResponseData doSave(Goods goods) {
        ResponseData responseData = new ResponseData();
        /**
         * responseCode = 2;系统错误
         */
        int responseCode = 2;
        String responseMsg = "系统错误";

        if (goods !=null && goods.getId() > 0) {
            long goodsId = goods.getId();
            Optional<Goods> optionalGoods = goodsRepository.findById(goodsId);
            Date nowTime = new Date();
            if (!optionalGoods.isPresent()) {
                goods.setCreateTime(nowTime);
                goods.setUpdateTime(nowTime);
                goods.setVersion(0);
                int updatedCount = goodsRepository.add(goods);
                if (updatedCount == 1) {
                    responseCode = ResponseCode.OK;
                    responseMsg = "操作成功";
                } else {
                    responseCode = 3;
                    responseMsg = "插入数据库失败";
                }
            } else {
                Goods existGoods = optionalGoods.get();
                log.info("ThisTopicExists");
                goods.setCreateTime(existGoods.getCreateTime());
                goods.setUpdateTime(nowTime);
                int updatedCount = goodsRepository.update(goods);
                if (updatedCount == 1) {
                    responseCode = ResponseCode.OK;
                    responseMsg = "操作成功";
                } else {
                    responseCode = 4;
                    responseMsg = "更新数据库失败";
                }
            }
        }

        responseData.setCode(responseCode);
        responseData.setMsg(responseMsg);
        return responseData;
    }

    public TableData<GoodsVO> byPageForTable(int page, int limit) {
        int pageValue = page > 0 ? (page -1) : 0;
        limit = limit > 0? limit : 10;
//        PageParam pageParam = new PageParam();
//        pageParam.setPage(pageValue);
//        pageParam.setLimit(limit);

        TableData<Goods> tableData = goodsRepository.byPage(pageValue, limit, 1);
        List<Goods> goodsList = tableData.getData();

        List<GoodsVO> voList = new ArrayList<GoodsVO>();
        if (goodsList!= null && goodsList.size()>0) {
            for (Goods goods : goodsList) {
                voList.add(ObjectConverter.goods2VO(goods));
            }
        }

        TableData<GoodsVO> result = new TableData<>();
        result.setCode(tableData.getCode());
        result.setMsg(tableData.getMsg());
        result.setCount(tableData.getCount());
        result.setData(voList);
        return result;
    }

    public int delete(long id) {
        return goodsRepository.delete(id);
    }
}
