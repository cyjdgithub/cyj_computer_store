package com.ncy.store.mapper;

import com.ncy.store.entity.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {
    List<District> findByParent(String parent);

    String findNameByCode(String code);
}
