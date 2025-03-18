package com.ncy.store.service;

import com.ncy.store.vo.ShopCarVO;

import java.util.List;

public interface IShopCarService {
    void addToShopCar(Integer uid, Integer pid, Integer amount, String username);

    List<ShopCarVO> getVOByUid(Integer uid);

    Integer addNum(Integer cid, Integer uid, String username);

    Integer reduceNum(Integer cid, Integer uid ,String username);

    void deleteGood(Integer cid);

    List<ShopCarVO> getVOByCid(Integer[] cids,Integer uid);

    void deleteGoods(Integer[] cids);






}
