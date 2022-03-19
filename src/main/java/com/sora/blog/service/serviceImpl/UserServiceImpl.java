package com.sora.blog.service.serviceImpl;

import com.sora.blog.mapper.UserMapper;
import com.sora.blog.pojo.User;
import com.sora.blog.service.UserService;
import com.sora.blog.util.MD5util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author Sora Chen
 * @Date 2022/2/5 13:56
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username) {
        User user = userMapper.queryByUsername(username);
        return userMapper.queryByUsername(username);
    }

    @Override
    @Transactional
    public User insertUser(User user) {
        if(user.getNickname()==null||user.getNickname().equals("")){
            user.setNickname(user.getUsername());
        }
        user.setPassword(MD5util.code(user.getPassword()));
        user.setType(5);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setAvatar("/static/image/avatar.jpg");
        userMapper.insertUser(user);
        return userMapper.queryByUsername(user.getUsername());
    }

    @Override
    @Transactional
    public User updateUser(User user, String password) {
        User userNew = new User();
        BeanUtils.copyProperties(user,userNew);
        userNew.setPassword(MD5util.code(password));
        userNew.setUpdateTime(new Date());
        int res = userMapper.updateUser(userNew);
        if(res!=1){
            return null;
        }
        return userMapper.queryByUsername(userNew.getUsername());
    }

    @Override
    public List<User> getFiveUser(Long userId) {
        List<User> users= userMapper.getSixUser();
        users.removeIf(user -> user.getId().equals(userId));
        return users;
    }

    @Override
    public List<User> getSixUser(){
        return userMapper.getSixUser();
    }
}
