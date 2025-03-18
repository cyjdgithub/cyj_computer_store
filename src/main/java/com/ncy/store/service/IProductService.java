package com.ncy.store.service;

import com.ncy.store.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findHotList();
    List<Product> findNewList();
    Product findById(Integer id);

}
