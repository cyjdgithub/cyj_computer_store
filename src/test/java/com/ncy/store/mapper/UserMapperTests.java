package com.ncy.store.mapper;

import com.ncy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("mmm");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public  void findByUsername(){
        User user = userMapper.findByUsername("mmm");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(30,"123456","cyj",new Date());
    }

    @Test
    public void updateByUid(){
        User user = new User();
        user.setUid(39);
        user.setPhone("123456789");
        user.setEmail("tim001@gmail.com");
        user.setGender(1);
        userMapper.updateUserByUid(user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(39,"/upload/avatar.png","admin",new Date());
    }

}
