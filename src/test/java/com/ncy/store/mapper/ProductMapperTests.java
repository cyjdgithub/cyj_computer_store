package com.ncy.store.mapper;

import com.ncy.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findHotList(){
        List<Product> list = productMapper.findHotList();
        System.out.println(list);
    }
    @Test
    public void findNewList(){
        List<Product> list = productMapper.findNewList();
        System.out.println(list);
    }

    @Test
    public void findById(){
        System.out.println(productMapper.findById(10000007));
    }

}
