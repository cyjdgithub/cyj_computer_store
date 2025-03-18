package com.ncy.store.mapper;

import com.ncy.store.entity.Order;
import com.ncy.store.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);

    List<OrderItem> selectOrderItem(String username);

    OrderItem getStatus(Integer id);

    Integer deleteOrderItem(Integer id);


}
