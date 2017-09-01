package com.ssm.modelCustom;

import com.ssm.model.Reply;

/**
 * 回复包装类
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

}
