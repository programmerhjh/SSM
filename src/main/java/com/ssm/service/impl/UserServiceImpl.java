package com.ssm.service.impl;

import com.ssm.dao.UserMapper;
import com.ssm.model.User;
import com.ssm.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by acer on 2017/6/24.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public User checkUserExist(String name, String password) {
        return userMapper.checkUserExist(name, password);
    }

    public boolean registeredUser(User user) {
        User newUser =  userMapper.registeredUser(user);
        if(newUser != null)
            return true;
        else
            return false;
    }
}
