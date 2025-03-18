package com.ncy.store.service;

import com.ncy.store.entity.Address;

import java.util.List;

public interface IAddressService {
    void addNewAddress(Integer uid,String username,Address address);

    List<Address> getAllAddressByUid(Integer uid);

    void setDefault(Integer aid,Integer uid, String username);

    void delete(Integer aid,Integer uid, String username);

    Address findByAid(Integer aid,Integer uid);


}
