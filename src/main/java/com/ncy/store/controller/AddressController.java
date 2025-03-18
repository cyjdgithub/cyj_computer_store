package com.ncy.store.controller;

import com.ncy.store.entity.Address;
import com.ncy.store.service.IAddressService;
import com.ncy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/address")
@RestController
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;
    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }
    @RequestMapping({"/",""})
    public JsonResult<List<Address>> showAllAddress(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<Address> list = addressService.getAllAddressByUid(uid);
        return new JsonResult<>(OK,list);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session){
        addressService.setDefault(aid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session){
        addressService.delete(aid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }


}
