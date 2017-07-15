package com.ssm.model;

/**
 * Created by acer on 2017/7/11.
 */
public class UserQueryVo extends User{

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
}
