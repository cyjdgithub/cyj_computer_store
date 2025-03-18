package com.ncy.store.mapper;

import com.ncy.store.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> list = districtMapper.findByParent("420000");
        for(District d: list){
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode(){
        String name = districtMapper.findNameByCode("430000");
        System.out.println(name);
    }
}
