package com.ssm.modelCustom;

import com.ssm.model.Comment;
import com.ssm.model.Post;
import com.ssm.model.User;

/**
 * Created by acer on 2017/7/19.
 */
public class CommentCustom extends Comment {
    private User user;
    private Post post;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
