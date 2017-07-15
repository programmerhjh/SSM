package com.ssm.mapper;

import com.ssm.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by acer on 2017/6/24.
 */
@Repository
public interface UserMapper extends Mapper<User>{
    User checkUserExist(@Param("name")String name,@Param("password")String password);
    //注册用户
    void registeredUser(@Param("name")String username,@Param("password")String password);
    //添加注册用户的行为记录
    void registerUserBehavior(@Param("user") User user);
    //更新用户在行为表中的信息
    void updateUserBehavior(@Param("user") User user);

    User findUserByName(@Param("name") String name);

    int checkUsernameIsExist(String username);
    //添加用户已完成完善个人资料的记录
    void updateUserHasCompleteFormation(@Param("user") User user);
    //验证用户是否进行过资料完善
    int checkUserHasCompleteFormation(@Param("user") User user);
    //添加验证通过的手机号码
    void addPassValidatePhone(@Param("phone")String phone,@Param("id")int id);
    //通过手机号码查找是否存在用户
    int checkUserExistByPhone(@Param("name")String name,@Param("phone")String phone);
    //添加用户新密码
    void addNewPassword(@Param("name")String name,@Param("password")String password);
}
