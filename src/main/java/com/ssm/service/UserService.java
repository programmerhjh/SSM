package com.ssm.service;

import com.ssm.model.Post;
import com.ssm.model.User;
import com.ssm.model.UserExpand;

/**
 * Created by acer on 2017/6/24.
 */
public interface UserService {
    UserExpand checkUserExist(String name,String password);
    void registeredUser(String username, String password);
    void addPassValidatePhone(String phone,int id);
    int checkUsernameIsExist(String username);
    int checkUserHasCompleteFormation(User user);
    void updateUserHasCompleteFormation(UserExpand user);
    int checkUserExistByPhone(String name,String phone);
    void addNewPassword(String name,String password);
}
