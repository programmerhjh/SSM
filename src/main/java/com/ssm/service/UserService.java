package com.ssm.service;

import com.ssm.model.User;

/**
 * Created by acer on 2017/6/24.
 */
public interface UserService {
    User checkUserExist(String name,String password);
    void registeredUser(String username,String password);
    void addPassValidatePhone(String phone,int id);
    int checkUsernameIsExist(String username);
}
