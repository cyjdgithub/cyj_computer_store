package com.ncy.store.controller;

import com.ncy.store.entity.Order;
import com.ncy.store.entity.OrderItem;
import com.ncy.store.service.IOrderService;
import com.ncy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/createorder")
    public JsonResult<Order> createOrder(Integer aid, Integer[] cids, HttpSession session){
        Order order = orderService.creatOrder(aid,cids,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK,order);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<OrderItem>> selectOrder(HttpSession session) {
        List<OrderItem> data = orderService.getOrderItem(getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }


    @RequestMapping("/status")
    public JsonResult<OrderItem> status(Integer id) {
        OrderItem order = orderService.selectStatus(id);
        return new JsonResult(OK, order);
    }

    @RequestMapping("{id}/deleteorderitem")
    public JsonResult<Void> deleteOrderItem(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
        return new JsonResult(OK);
    }


}
