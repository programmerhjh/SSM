package com.ssm.modelCustom;

import com.ssm.model.User;

/**
 * 包含了用户头像地址的用户包装类
 * Created by acer on 2017/7/24.
 */
public class UserExpand extends User {
    private String headAddress;

    public String getHeadAddress() {
        return headAddress;
    }

    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress;
    }

    @Override
    public String toString() {
        return "UserExpand{" +
                "headAddress='" + headAddress + '\'' +
                '}';
    }
}
