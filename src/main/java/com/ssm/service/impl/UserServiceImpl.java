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

    public void registeredUser(String username,String password) {
        userMapper.registeredUser(username,password);
    }

    public void addPassValidatePhone(String phone ,int id) {
        userMapper.addPassValidatePhone(phone,id);
    }

    public int checkUsernameIsExist(String username) {
        return userMapper.checkUsernameIsExist(username);
    }
}
