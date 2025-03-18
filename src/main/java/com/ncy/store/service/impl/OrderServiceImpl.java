package com.ncy.store.service.impl;

import com.ncy.store.entity.Address;
import com.ncy.store.entity.Order;
import com.ncy.store.entity.OrderItem;
import com.ncy.store.mapper.OrderMapper;
import com.ncy.store.service.IAddressService;
import com.ncy.store.service.IOrderService;
import com.ncy.store.service.IShopCarService;
import com.ncy.store.service.ex.DeleteException;
import com.ncy.store.service.ex.InsertException;
import com.ncy.store.service.ex.OrderNotFoundException;
import com.ncy.store.vo.ShopCarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IShopCarService shopCarService;


    @Override
    public Order creatOrder(Integer aid, Integer[] cids, Integer uid, String username) {
        Address address = addressService.findByAid(aid,uid);
        List<ShopCarVO> list = shopCarService.getVOByCid(cids,uid);

        long totalPrice = 0;

        for(ShopCarVO shopCarVO: list){
            totalPrice += shopCarVO.getRealPrice()* shopCarVO.getNum();
        }

        Order order = new Order();

        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());

        order.setTotalPrice(totalPrice);

        order.setStatus(0);

        order.setOrderTime(new Date());

        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifyUser(username);
        order.setModifyTime(new Date());


        Integer rows = orderMapper.insertOrder(order);
        if(rows!=1){
            throw new InsertException("插入订单时产生异常");
        }

        for (ShopCarVO shopCarVO: list){
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            orderItem.setPid(shopCarVO.getPid());
            orderItem.setTitle(shopCarVO.getTitle());
            orderItem.setImage(shopCarVO.getImage());
            orderItem.setPrice(shopCarVO.getRealPrice());
            orderItem.setNum(shopCarVO.getNum());
            // 补全数据：4项日志
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifyUser(username);
            orderItem.setModifyTime(new Date());
            Integer rows2 = orderMapper.insertOrderItem(orderItem);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }

        return order;
    }

    @Override
    public List<OrderItem> getOrderItem(String username) {
        List<OrderItem> list = orderMapper.selectOrderItem(username);
        if (list == null) {
            throw new OrderNotFoundException("未找到订单信息");
        }
        return list;
    }

    @Override
    public OrderItem selectStatus(Integer id) {
        OrderItem orderItem = orderMapper.getStatus(id);
        return orderItem;
    }

    @Override
    public void deleteOrder(Integer id) {
        Integer rows = orderMapper.deleteOrderItem(id);
        if (rows != 1) {
            throw new DeleteException("删除订单商品失败");
        }
    }
}
