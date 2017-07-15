package com.ssm.model;

/**
 * 用户行为实体类
 * Created by acer on 2017/7/10.
 */
public class UserBehavior {
    private int id;
    private int userHasCompleteFormation;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public void setId(int id) {
        this.id = id;
    }


    public int getUserHasCompleteFormation() {
        return userHasCompleteFormation;
    }

    public void setUserHasCompleteFormation(int userHasCompleteFormation) {
        this.userHasCompleteFormation = userHasCompleteFormation;
    }
}
