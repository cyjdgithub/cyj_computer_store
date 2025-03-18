package com.ncy.store.controller;

import com.ncy.store.service.IShopCarService;
import com.ncy.store.util.JsonResult;
import com.ncy.store.vo.ShopCarVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shopcar")
public class ShopCarController extends BaseController{
    @Autowired
    private IShopCarService shopCarService;

    @RequestMapping("/addtoshopcar")
    public JsonResult addToShopCar(Integer pid, Integer amount, HttpSession session) {

        shopCarService.addToShopCar(getuidFromSession(session), pid, amount, getUsernameFromSession(session));
        return new JsonResult(OK);
    }

    @RequestMapping({"/",""})
    public JsonResult<List<ShopCarVO>> getVOByUid(HttpSession session){
        List<ShopCarVO> list = shopCarService.getVOByUid(getuidFromSession(session));
        return new JsonResult<>(OK,list);
    }

    @RequestMapping("{cid}/addnum")
    public JsonResult<Integer> addNum(@PathVariable("cid")Integer cid, HttpSession session){
        Integer data = shopCarService.addNum(cid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{cid}/reducenum")
    public JsonResult<Integer> reduceNum(@PathVariable("cid")Integer cid, HttpSession session){
        Integer data = shopCarService.reduceNum(cid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{cid}/delete")
    public JsonResult<Void> deleteGood(@PathVariable("cid") Integer cid){
        shopCarService.deleteGood(cid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/list")
    public JsonResult<List<ShopCarVO>> getVOByCid(Integer[] cids, HttpSession session){
        List<ShopCarVO> data = shopCarService.getVOByCid(cids,getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("/deletegoods")
    public JsonResult<Void> deleteGoods(Integer[] cids){
        shopCarService.deleteGoods(cids);
        return  new JsonResult<>(OK);
    }
}
