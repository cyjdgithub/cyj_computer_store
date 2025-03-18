package com.ncy.store.mapper;

import com.ncy.store.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(39);
        address.setPhone("188888888");
        address.setName("timmmy");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer count = addressMapper.countByUid(12);
        System.out.println(count);
    }
    @Test
    public void findAddressByUid(){
        List<Address> list = addressMapper.findAddressByUid(12);
        System.out.println(list);
    }


    @Test
    public void updateNonDefault(){
        addressMapper.updateNonDefault(39);
    }

    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(14,"admin", new Date());
    }

    @Test
    public void findByAid(){
        System.out.println(addressMapper.findByAid(14));
    }

    @Test
    public void deletedByAid(){
        addressMapper.deletedByAid(14);
    }

    @Test
    public void findLastModified(){
        Address address = new Address();
        address = addressMapper.findLastModified(12);
        System.out.println(address);
    }

}
