package com.ssm.service.impl;

import com.ssm.mapper.UserMapper;
import com.ssm.model.User;
import com.ssm.modelCustom.UserExpand;
import com.ssm.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户业务层实现
 * Created by acer on 2017/6/24.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    public void deleteUserList(List<Integer> list) {
        userMapper.deleteUserList(list);
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    public List<User> selectAllUser() {
        return userMapper.selectAll();
    }

    public UserExpand checkUserExist(String name, String password) {
        return userMapper.checkUserExist(name, password);
    }

    public void registeredUser(String username, String password) {
        userMapper.registeredUser(username,password);
        User user = userMapper.findUserByName(username);
        userMapper.registerUserBehavior(user);
    }

    public void addPassValidatePhone(String phone ,int id) {
        userMapper.addPassValidatePhone(phone,id);
    }

    public int checkUsernameIsExist(String username) {
        return userMapper.checkUsernameIsExist(username);
    }

    public int checkUserHasCompleteFormation(User user) {
        return userMapper.checkUserHasCompleteFormation(user);
    }

    public void updateUserHasCompleteFormation(UserExpand user){
        userMapper.updateUserHasCompleteFormation(user);
        userMapper.updateUserBehavior(user);
    }

    public int checkUserExistByPhone(String name, String phone) {
        return userMapper.checkUserExistByPhone(name,phone);
    }

    public void addNewPassword(String name, String password) {
        userMapper.addNewPassword(name,password);
    }
}
