package org.flylib.mall.shop.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoodsRepositoryTest {
    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void minus() {
        long goodsId = 436665195520065536L;
        for (int i = 0; i < 80; i++) {
            goodsRepository.minus(goodsId, 1);
        }
        System.out.println("-----Done---");
    }
}