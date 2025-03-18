package com.ncy.store.mapper;

import com.ncy.store.entity.Address;
import com.ncy.store.entity.District;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AddressMapper {
    Integer insert(Address address); //插入用户收货地址数据，返回行数

    Integer countByUid(Integer uid);

    List<Address> findAddressByUid(Integer uid);

    Integer updateNonDefault(Integer uid);
    Integer updateDefaultByAid(@Param("aid") Integer aid, @Param("modifyUser")String modifyUser, @Param("modifyTime") Date modifyTime);

    Address findByAid(Integer aid);

    Integer deletedByAid(Integer aid);

    Address findLastModified(Integer uid);
}
