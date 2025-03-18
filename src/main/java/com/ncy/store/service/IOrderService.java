package com.ncy.store.service;

import com.ncy.store.entity.Order;
import com.ncy.store.entity.OrderItem;

import java.util.List;

public interface IOrderService {
    Order creatOrder(Integer aid,Integer[] cids,Integer uid,String username);

    List<OrderItem> getOrderItem(String username);

    OrderItem selectStatus(Integer id);

    void deleteOrder(Integer id);
}
