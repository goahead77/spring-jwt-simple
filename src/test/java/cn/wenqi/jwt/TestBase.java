package cn.wenqi.jwt;

import cn.wenqi.jwt.model.Goods;
import cn.wenqi.jwt.repo.GoodsRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author wenqi
 * @since v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestBase {


    @Autowired
    private GoodsRepo goodsRepo;

    @Test
    public void addTest(){
        for (int i = 1; i < 100000L; i++) {
            Goods order=new Goods();
            order.setId((long) i);
            order.setCreateTime(new Date());
            order.setName("test"+i);
            goodsRepo.save(order);
            log.info("插入成功：{}",i);
        }
    }


}
