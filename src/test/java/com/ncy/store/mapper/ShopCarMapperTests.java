package com.ncy.store.mapper;

import com.ncy.store.entity.Product;
import com.ncy.store.entity.ShopCar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PublicKey;
import java.util.Date;

@SpringBootTest
public class ShopCarMapperTests {

    @Autowired
    private ShopCarMapper shopCarMapper;



    @Test
    public void addProduct(){
        ShopCar shopCar = new ShopCar();
        shopCar.setUid(39);
        shopCar.setPid(10000011);
        shopCar.setNum(2);
        shopCar.setPrice(3000L);
        shopCarMapper.addProduct(shopCar);
    }

    @Test
    public void updateNumByCid(){
        shopCarMapper.updateNumByCid(27,4,"xiaoming",new Date());
    }

    @Test
    public void findByUidAndPid(){
        ShopCar shopCar = new ShopCar();
        shopCar = shopCarMapper.findByUidAndPid(12,10000021);
        System.out.println(shopCar);

    }

    @Test
    public void findVOByUid(){
        System.out.println(shopCarMapper.findVOByUid(39));
    }

    @Test
    public void findByCid(){
        System.out.println(shopCarMapper.findByCid(29));
    }

    @Test
    public void deleteGood(){
        shopCarMapper.deleteGood(30);
    }

    @Test
    public void findVOByCid(){
        Integer[] cids = {20,27,29,31};
        System.out.println(shopCarMapper.findVOByCid(cids));
    }
}
