package com.ncy.store.mapper;

import com.ncy.store.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class OrderMapperTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder(){
        Order order = new Order();
        order.setUid(39);
        order.setAid(25);
        order.setRecvName("ccc");
        order.setRecvPhone("111111111");
        order.setRecvProvince("湖北省");
        order.setRecvCity("武汉市");
        order.setRecvArea("武昌区");
        order.setOrderTime(new Date());
        order.setCreatedTime(new Date());
        order.setModifyTime(new Date());
        order.setCreatedUser("xiaoming");
        order.setModifyUser("xiaoming");
        Integer rows = orderMapper.insertOrder(order);
        if(rows!=1){
            System.err.println("失败");
        }
    }

    @Test
    public void selectOrderItem(){
        System.err.println(orderMapper.selectOrderItem("123"));
    }

    @Test
    public void getStatus(){
        System.err.println(orderMapper.getStatus(22));
    }

    @Test
    public void deleteOrderItem(){
        orderMapper.deleteOrderItem(22);
    }
}
