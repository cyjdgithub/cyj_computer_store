package com.ncy.store.service;

import com.ncy.store.entity.User;
import com.ncy.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void reg(){
        try {
            User user =  new User();
            user.setUsername("1234");
            user.setPassword("qqq");
            userService.reg(user);
            System.out.println("OK!");
        } catch (ServiceException e) {
//            throw new RuntimeException(e);
            System.out.println(e.getClass().getSimpleName());

            System.out.println(e.getMessage());//具体描述信息
        }
    }

    @Test
    public void login(){
        User user = userService.login("tim001","1234");
        System.out.println(user);
    }

    @Test
    public void changePassword(){
        userService.changePassword(39,"tim001","123456","1234");
    }

    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(39));
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("111111111");
        user.setEmail("tim001@qq.com");
        user.setGender(0);
        userService.changeInfo(39,"admin",user);
    }

    @Test
    public  void changeAvatar(){
        userService.changeAvatar(39,"/upload/avatar.png","tim001");
    }
}
