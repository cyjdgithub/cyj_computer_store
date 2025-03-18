package com.ncy.store.mapper;

import com.ncy.store.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findHotList();
    List<Product> findNewList();

    Product findById(Integer id);
}
