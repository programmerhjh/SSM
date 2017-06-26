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
    User registeredUser(User user);
}
