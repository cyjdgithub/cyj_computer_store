package com.ncy.store.controller;

import com.ncy.store.entity.District;
import com.ncy.store.service.IDistrictService;
import com.ncy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/district")
@RestController
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService districtService;

    @RequestMapping({"/",""})
    private JsonResult<List<District>> getByParent(String parent){
        List<District> data = districtService.getParent(parent);
        return new JsonResult<>(OK,data);
    }



}
