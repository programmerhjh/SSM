package com.ssm.model;

/**
 * Created by acer on 2017/7/24.
 */
public class ReplyCustom extends Reply {

    private UserExpand replyUser;

    public UserExpand getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(UserExpand replyUser) {
        this.replyUser = replyUser;
    }

    @Override
    public String toString() {
        return "ReplyCustom{" +
                "replyUser=" + replyUser +
                '}';
    }
}
