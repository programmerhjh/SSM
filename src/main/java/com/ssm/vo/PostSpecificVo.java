package com.ssm.vo;

import com.ssm.model.Post;
import com.ssm.model.User;
import com.ssm.model.UserExpand;

import java.util.List;

/**
 * Created by acer on 2017/7/24.
 */
public class PostSpecificVo extends Post {

    private UserExpand user;

    private List<CommentAndReplyVo> commentAndReplyVos;

    public UserExpand getUser() {
        return user;
    }

    public void setUser(UserExpand user) {
        this.user = user;
    }

    public List<CommentAndReplyVo> getCommentAndReplyVos() {
        return commentAndReplyVos;
    }

    public void setCommentAndReplyVos(List<CommentAndReplyVo> commentAndReplyVos) {
        this.commentAndReplyVos = commentAndReplyVos;
    }

    @Override
    public String toString() {
        return "PostSpecificVo{" +
                "user=" + user +
                ", commentAndReplyVos=" + commentAndReplyVos +
                '}';
    }
}
