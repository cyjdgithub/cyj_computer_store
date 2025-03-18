package com.ncy.store.mapper;

import com.ncy.store.entity.ShopCar;
import com.ncy.store.vo.ShopCarVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface ShopCarMapper {
    Integer addProduct(ShopCar shopCar);

    Integer updateNumByCid(
            Integer cid,
            Integer num,
            String modifyUser,
            Date modifyTime);

    ShopCar findByUidAndPid(Integer uid, Integer pid);

    List<ShopCarVO> findVOByUid(Integer uid);

    ShopCar findByCid(Integer cid);

    Integer deleteGood(Integer cid);

    List<ShopCarVO> findVOByCid(Integer[] cids);

    Integer deleteGoods(Integer[] cids);





}
