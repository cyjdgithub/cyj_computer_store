package com.ncy.store.service;

import com.ncy.store.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictServiceTests {
    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent(){
        List<District> list = districtService.getParent("86");
        for(District d:list){
            System.err.println(d);
        }
    }

}
