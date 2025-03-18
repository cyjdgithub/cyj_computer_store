package com.ncy.store.service.impl;

import com.ncy.store.entity.Order;
import com.ncy.store.entity.Product;
import com.ncy.store.entity.ShopCar;
import com.ncy.store.mapper.OrderMapper;
import com.ncy.store.mapper.ProductMapper;
import com.ncy.store.mapper.ShopCarMapper;
import com.ncy.store.service.IShopCarService;
import com.ncy.store.service.ex.*;
import com.ncy.store.vo.ShopCarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ShopCarServiceImpl implements IShopCarService {
    @Autowired
    private ShopCarMapper shopCarMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public void addToShopCar(Integer uid, Integer pid, Integer amount, String username) {
        ShopCar result = shopCarMapper.findByUidAndPid(uid,pid);
        if(result == null){
            ShopCar shopCar = new ShopCar();
            shopCar.setUid(uid);
            shopCar.setPid(pid);
            shopCar.setNum(amount);

            Product p = productMapper.findById(pid);
            shopCar.setPrice(p.getPrice());

            shopCar.setCreatedUser(username);
            shopCar.setCreatedTime(new Date());
            shopCar.setModifyTime(new Date());
            shopCar.setModifyUser(username);
            Integer rows = shopCarMapper.addProduct(shopCar);
            if(rows!=1){
                throw new InsertException("数据插入时产生未知异常");
            }
            }else {
            Integer cid = result.getCid();
            Integer num = result.getNum()+amount;

            Integer row = shopCarMapper.updateNumByCid(cid,num,username,new Date());
            if (row != 1) {
                throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
            }

        }
    }

    @Override
    public List<ShopCarVO> getVOByUid(Integer uid) {
        return shopCarMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        ShopCar result = shopCarMapper.findByCid(cid);
        if(result == null){
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("没有访问权限");
        }
        Integer num = result.getNum() +1 ;
        Integer rows = shopCarMapper.updateNumByCid(cid,num,username,new Date());
        if(rows !=1){
            throw new UpdateException("数量更新异常");
        }
        return  num;

    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        ShopCar result = shopCarMapper.findByCid(cid);
        if(result == null){
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("没有访问权限");
        }
        Integer num = result.getNum() - 1 ;
        Integer rows = shopCarMapper.updateNumByCid(cid,num,username,new Date());
        if(rows !=1){
            throw new UpdateException("数量更新异常");
        }
        return  num;
    }

    @Override
    public void deleteGood(Integer cid) {
        Integer rows = shopCarMapper.deleteGood(cid);
        if(rows < 1){
            throw new DeleteException("删除数据时发生异常");
        }
    }

    @Override
    public List<ShopCarVO> getVOByCid(Integer[] cids, Integer uid) {
//        return null;
        List<ShopCarVO> list = shopCarMapper.findVOByCid(cids);
        Iterator<ShopCarVO> iterator = list.iterator();
        while (iterator.hasNext()){
            ShopCarVO shopCarVO = iterator.next();
            if(!shopCarVO.getUid().equals(uid)){
                //表示不属于当前用户
                list.remove(shopCarVO);
            }
        }
        return list;
    }

    @Override
    public void deleteGoods(Integer[] cids) {
        Integer result = shopCarMapper.deleteGoods(cids);
        if(result<1){
            throw new DeleteException("删除时发生未知异常");
        }

    }



}
