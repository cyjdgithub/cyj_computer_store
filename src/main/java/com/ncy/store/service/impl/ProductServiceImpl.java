package com.ncy.store.service.impl;

import com.ncy.store.entity.Product;
import com.ncy.store.mapper.ProductMapper;
import com.ncy.store.service.IProductService;
import com.ncy.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product p:list){
            p.setPriority(null);
            p.setCreatedTime(null);
            p.setCreatedUser(null);
            p.setModifyTime(null);
            p.setModifyUser(null);
        }
        return list;
    }

    @Override
    public List<Product> findNewList() {
        List<Product> list = productMapper.findNewList();
        for (Product p:list){
            p.setPriority(null);
            p.setCreatedTime(null);
            p.setCreatedUser(null);
            p.setModifyTime(null);
            p.setModifyUser(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Product result = productMapper.findById(id);
        if(result == null){
            throw new ProductNotFoundException("商品信息未找到！");
        }
        result.setPriority(null);
        result.setCreatedUser(null);
        result.setCreatedTime(null);
        result.setModifyUser(null);
        result.setModifyTime(null);
        return result;
    }
}
