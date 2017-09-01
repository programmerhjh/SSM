package com.ssm.mapper;

import com.ssm.model.User;
import com.ssm.modelCustom.UserExpand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户dao层
 * Created by acer on 2017/6/24.
 */
@Repository
public interface UserMapper extends Mapper<User>{

    //删除一个集合中的用户
    void deleteUserList(List<Integer> list);

    //删除用户
    void deleteUser(int id);

    //检查用户是否存在
    UserExpand checkUserExist(@Param("name")String name,@Param("password")String password);
    //注册用户
    void registeredUser(@Param("name")String username,@Param("password")String password);
    //添加注册用户的行为记录
    void registerUserBehavior(@Param("user") User user);
    //更新用户在行为表中的信息
    void updateUserBehavior(@Param("user") User user);

    //通过用户名返回一个User对象
    User findUserByName(@Param("name") String name);

    //检测用户名是否重复
    int checkUsernameIsExist(String username);
    //添加用户已完成完善个人资料的记录
    void updateUserHasCompleteFormation(@Param("user") UserExpand user);
    //验证用户是否进行过资料完善
    int checkUserHasCompleteFormation(@Param("user") User user);
    //添加验证通过的手机号码
    void addPassValidatePhone(@Param("phone")String phone,@Param("id")int id);
    //通过手机号码查找是否存在用户
    int checkUserExistByPhone(@Param("name")String name,@Param("phone")String phone);
    //添加用户新密码
    void addNewPassword(@Param("name")String name,@Param("password")String password);
}
