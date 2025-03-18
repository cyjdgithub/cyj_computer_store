package com.ncy.store.service;

import com.ncy.store.entity.District;

import java.util.List;

public interface IDistrictService {
    List<District> getParent(String parent);

    String getNameByCode(String code);
}
