package com.ncy.store.service.impl;

import com.ncy.store.entity.User;
import com.ncy.store.mapper.UserMapper;
import com.ncy.store.service.IUserService;
import com.ncy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
//实现接口
public class UserServiceImpl implements IUserService {
    @Autowired
    //调用mapper方法，先把对象生成出来
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        //调用mapper的findbyusername(username)判断是否被注册过
        User result = userMapper.findByUsername(user.getUsername());
        if(result != null){
            throw new UserNameDuplicatedException("用户名被占用！");
        }

        //加密处理，MD5密码
        //串 + password + 串 ------MD5算法加密
        // 盐值 + password + 盐值 ------ 盐值就是一个随机字符串

        String oldPassword = user.getPassword();
        //随机生成一个盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        //讲密码和盐值作为以一个整体进行加密处理

        String md5Password = getMD5Passwoed(oldPassword,salt);

        //将加密之后的饭密码重新补全设置到user对象中

        user.setPassword(md5Password);



        //补全：is_delete设置成0
        user.setIsDelete(0);

        //补全数据：4个日志信息
        user.setCreatedUser(user.getUsername());
        user.setModifyUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifyTime(date);

        Integer rows = userMapper.insert(user);
        if(rows !=1){
            throw new InsertException("在用户注册过程中产生了未知的异常！");
        }
    }

    @Override
    public User login(String username, String password) {
        //根据用户名称查询用户是否存在，不存在则抛异常
        User result = userMapper.findByUsername(username);
        if(result == null){
            throw new UserNotFoundException("用户不存在");
        }
        //检测密码是否匹配
        //1.获取加密之后的密码
        //2.比较
        //2.1 获取盐值
        //2.2 将用户的密码按照相同的md5算法的规则进行加密
        String oldPassword = result.getPassword();
        String salt = result.getSalt();
        String newMd5Password = getMD5Passwoed(password,salt);
        if(!newMd5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }

        //判断is delete字段的值
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

        //提升系统性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(username);
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result == null || Objects.equals(result.getIsDelete(), 1)){
            throw new UserNotFoundException("用户不存在");
        }
        //比较
        String oldMD5Password = getMD5Passwoed(oldPassword,result.getSalt());
        if(!result.getPassword().equals(oldMD5Password)){
            throw new PasswordNotMatchException("密码错误！");
        }
        //新密码，先加密，再更新
        String newMD5Password = getMD5Passwoed(newPassword,result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid,newMD5Password,username,new Date());
        if(rows!=1){
            throw new UpdateException("插入时发生了未知异常！");
        }

    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result == null || Objects.equals(result.getIsDelete(), 1)){
            throw new UserNotFoundException("用户未找到");
        }

        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if(result == null || Objects.equals(result.getIsDelete(), 1)){
            throw new UserNotFoundException("用户数据不存在！");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifyUser(username);
        user.setModifyTime(new Date());

        Integer rows = userMapper.updateUserByUid(user);
        if(rows!=1){
            throw  new UpdateException("数据更新时产生了异常！");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if(result == null || Objects.equals(result.getIsDelete(), 1)){
            throw new UserNotFoundException("用户数据不存在！");
        }
        Integer rows = userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if(rows!=1){
            throw  new UpdateException("数据更新时产生了异常！");
        }
    }


    /*定义一个MD5算法的加密*/
    private String getMD5Passwoed(String password, String salt){

        for(int i = 0; i<3;i++){
            //md5方法调用
            password = DigestUtils.md5DigestAsHex((salt+password+salt).toUpperCase().getBytes());
        }

        //返回加密之后的
       return password;

    }
}
