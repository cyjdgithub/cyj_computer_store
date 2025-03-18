package com.ncy.store.service.impl;

import com.ncy.store.entity.District;
import com.ncy.store.mapper.DistrictMapper;
import com.ncy.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);

        for(District d : list){
            d.setId(null);
            d.setParent(null);
        }

        return list;
    }

    @Override
    public String getNameByCode(String code) {
        String name = districtMapper.findNameByCode(code);
        return name;
    }
}
