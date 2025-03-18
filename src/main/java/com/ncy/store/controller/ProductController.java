package com.ncy.store.controller;

import com.ncy.store.entity.BaseEntity;
import com.ncy.store.entity.Product;
import com.ncy.store.service.IProductService;
import com.ncy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> findHotList(){
        List<Product> list = productService.findHotList();
        return new JsonResult<>(OK,list);
    }

    @RequestMapping("/new_list")
    public JsonResult<List<Product>> findNewList(){
        List<Product> list = productService.findNewList();
        return new JsonResult<>(OK,list);
    }

    @RequestMapping("/{id}/good")
    public JsonResult<Product> findById(@PathVariable("id")Integer id){
        Product p = productService.findById(id);
        return new JsonResult<>(OK,p);


    }
}
