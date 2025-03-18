package com.ncy.store.service.impl;

import com.ncy.store.entity.Address;
import com.ncy.store.mapper.AddressMapper;
import com.ncy.store.service.IAddressService;
import com.ncy.store.service.IDistrictService;
import com.ncy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //
        Integer count = addressMapper.countByUid(uid);
        if(count>= maxCount){
            throw new AddressCountLimitException("地址数量超过最大值");
        }

        String proviceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());

        address.setProvinceName(proviceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);




        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setModifyUser(username);
        address.setCreatedTime(new Date());
        address.setModifyTime(new Date());

        Integer rows = addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("用户插入地址时发生了异常");
        }
    }

    @Override
    public List<Address> getAllAddressByUid(Integer uid) {
        List<Address> list = addressMapper.findAddressByUid(uid);
        for(Address address:list){
            //address.setAid(null);
            //address.setUid(null);
            address.setProvinceCode(null);
            address.setAreaCode(null);
            address.setCityCode(null);
            address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifyTime(null);
            address.setModifyUser(null);
        }

        return list;

    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result == null){
            throw new AddressNotFoundException("收获地址未找到");
        }

        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

        int rows = addressMapper.updateNonDefault(uid);
        if(rows< 1){
            throw new UpdateException("更新数据产生未知的异常");
        }
        rows = addressMapper.updateDefaultByAid(aid,username,new Date());
        if(rows !=1){
            throw new UpdateException("更新数据产生了未知的异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result == null){
            throw new AddressNotFoundException("地址不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

        Integer rows = addressMapper.deletedByAid(aid);
        if(rows!=1){
            throw new DeleteAddressException("删除数据时产生了未知的异常！");
        }

        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            //如果查出数据为0，说明刚刚删除的就是最后一条数据，此时也不需要重新找一个地址设置为默认，直接退出就行
            return;
        }

        int isDefaultValue = (result.getIsDefault() != null) ? result.getIsDefault().intValue() : 0; // 默认值为 0

        if (isDefaultValue == 0) {
            return;
        }


        Address address = addressMapper.findLastModified(uid);

        rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新时发生未知异常");
        }
    }

    @Override
    public Address findByAid(Integer aid, Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("未找到该地址");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        //将不需要的字段数据设置为空
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifyUser(null);
        address.setModifyTime(null);
        return address;
    }

}
