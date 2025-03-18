package com.ncy.store.service;

import com.ncy.store.entity.User;
import org.springframework.stereotype.Service;


public interface IUserService {
    void reg(User user);//user类型参数列表
    User login(String username, String password);

    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    User getByUid(Integer uid);

    void changeInfo(Integer uid, String username,User user);

    void changeAvatar(Integer uid, String avatar,String username);

}
