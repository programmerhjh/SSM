package com.ssm.dao;

import com.ssm.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by acer on 2017/6/24.
 */
@Repository
public interface UserMapper {
    User checkUserExist(@Param("name")String name,@Param("password")String password);
    void registeredUser(@Param("name")String username,@Param("password")String password);
    int checkUsernameIsExist(String username);
    void addPassValidatePhone(@Param("phone")String phone,@Param("id")int id);    //添加验证通过的手机号码
}
