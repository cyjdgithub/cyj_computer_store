package com.ncy.store.service;

import com.ncy.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class AddressServiceTests {

    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("123123123");
        address.setName("男朋友");
        addressService.addNewAddress(12,"admin",address);
    }

    @Test
    public void setDefault(){
        addressService.setDefault(25,39,"xiaoming");
    }

    @Test
    public void delete(){
        addressService.delete(18,10,"xiaoming");
    }


}
