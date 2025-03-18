package com.ncy.store.service;

import com.ncy.store.entity.ShopCar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShopCarServiceTests {
    @Autowired
    private IShopCarService shopCarService;

    @Test
    public void addToShopCar(){
        shopCarService.addToShopCar(39,10000017,10,"admin");
    }
}
