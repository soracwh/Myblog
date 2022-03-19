package com.sora.blog.service;

import com.sora.blog.pojo.User;

import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/5 13:54
 * @Version 1.0
 */
public interface UserService {

    User checkUser(String username);

    User insertUser(User user);

    User updateUser(User user,String password);

    List<User> getFiveUser(Long userId);

    List<User> getSixUser();
}
