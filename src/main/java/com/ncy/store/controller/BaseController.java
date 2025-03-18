package com.ncy.store.controller;

import com.ncy.store.controller.ex.*;
import com.ncy.store.service.ex.*;
import com.ncy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.web.bind.annotation.ExceptionHandler;

//控制层类的基类
public class BaseController {
    public static final int OK = 200; //操作成功的状态码
    @ExceptionHandler({ServiceException.class,FileUploadException.class}) //用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UserNameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已被占用");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生了未知的异常");
        }else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("用户数据不存在");
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("用户输入的密码错误");
        }else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("更新数据时产生了未知的异常");
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("文件为空");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件大小异常");
        } else if (e instanceof FileStateException) {
            result.setState(6002);
            result.setMessage("文件状态异常");
        } else if (e instanceof FileTypeException) {
            result.setState(6003);
            result.setMessage("文件类型异常");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("文件读写异常");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("用户地址超出上限异常");
        }else if (e instanceof AccessDeniedException) {
            result.setState(4004);
            result.setMessage("数据非法访问的异常");
        }else if (e instanceof AddressNotFoundException) {
            result.setState(4005);
            result.setMessage("用户收货数据不存在的异常");
        }else if (e instanceof DeleteAddressException) {
            result.setState(4006);
            result.setMessage("删除数据异常！");
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("商品数据不存在异常！");
        }
        return result;

    }

    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
