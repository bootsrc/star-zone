package org.flylib.mall.shop.controller;

import org.flylib.mall.common.dto.ResponseData;
import org.flylib.mall.common.dto.TableData;
import org.flylib.mall.shop.entity.Goods;
import org.flylib.mall.shop.model.PageForm;
import org.flylib.mall.shop.service.GoodsService;
import org.flylib.mall.shop.util.ObjectConverter;
import org.flylib.mall.shop.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("goodsManage")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GoodsManageController {
    @Autowired
    private GoodsService goodsService;

//    @RequestMapping("test")
//    public ResponseData test(){
//        goodsService.test();
//        ResponseData responseData = ResponseData.newOK();
//        return responseData;
//    }
//
//    @RequestMapping("test1")
//    public String test1() {
//        int maxActive = 200;
//        ExecutorService threadPool = Executors.newFixedThreadPool(maxActive);
//        long goodsId = 436665195520065536L;
//        for (int i = 0; i < 1500; i++) {
//            int j = i;
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    goodsService.test1();
//                }
//            };
//            threadPool.submit(runnable);
//        }
//        System.out.println("-----Done---");
//        return "test1 done";
//    }

//    @RequestMapping("listBase")
//    public TableData listBase(int page, int limit) {
//        page = page < 1 ? 0 : page - 1;
//        limit = limit < 0 ? 10 : limit;
//        return goodsService.findAllByLayui(page, limit, 0);
//    }
//
//    @RequestMapping("list")
//    public TableData list(int page, int limit) {
//        page = page < 1 ? 0 : page - 1;
//        limit = limit < 0 ? 10 : limit;
//        return goodsService.findAllByLayui(page, limit, 1);
//    }

    //    @RequestMapping(value = "saveProfile", consumes = "application/json;charset=UTF-8")
    @RequestMapping(value = "save")
    public ResponseData save(GoodsVO goodsVO) {
        Goods goods = ObjectConverter.goodsVO2Obj(goodsVO);
        ResponseData responseData = goodsService.save(goods);
        return responseData;
    }

    @RequestMapping("/getTable")
    public TableData list(@RequestBody PageForm pageForm) {
        return goodsService.byPageForTable(pageForm.getPageNo(), pageForm.getPageSize());
    }

    @RequestMapping("/delete")
    public ResponseData delete(@RequestBody GoodsVO topicVO) {
        if (topicVO != null && !StringUtils.isEmpty(topicVO.getId())) {
            goodsService.delete(Long.valueOf(topicVO.getId()));
        }
        ResponseData responseData = ResponseData.newOK();
        return responseData;
    }
}
