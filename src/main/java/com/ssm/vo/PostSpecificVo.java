package com.ssm.vo;

import com.ssm.model.Post;
import com.ssm.modelCustom.UserExpand;

import java.util.List;

/**
 * Created by acer on 2017/7/24.
 */
public class PostSpecificVo extends Post {

    private UserExpand user;

    private List<CommentAndReplyVo> commentAndReplyVos;

    public PostSpecificVo(){}

    public PostSpecificVo(String postName, Integer postAuthor, Integer postClicktimes, String postCategory,String postPost) {
        super(postName, postAuthor, postClicktimes, postCategory,postPost);
    }

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
