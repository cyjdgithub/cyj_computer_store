package com.ncy.store.mapper;

//用户模块持久层接口

import com.ncy.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserMapper {
    /*
    * 1.用户数据插入（注册）
    * 2.查询是否存在，因为是unique username
    * */

    //插入用户数据,返回受影响行数（增，删，改）
    Integer insert(User user);

    //根据用户名查询用户数据,找到则返回用户数据，没有则返回null值
    User findByUsername(String username);

    /*
    * 根据用户uid查询用户数据
    * return 找到则返回对象，反之返回null
    * @param password
    * @param modifiedUser
    * @param modifiedTime
    * */
    Integer updatePasswordByUid(Integer uid, String password, String modifyUser, Date modifyTime);

    User findByUid(Integer uid);

    Integer updateUserByUid(User user);

    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifyUser") String modifyUser,
                              @Param("modifyTime") Date modifyTime);
}
