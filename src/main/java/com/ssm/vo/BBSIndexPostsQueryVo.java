package com.ssm.vo;

import com.ssm.model.Post;
import com.ssm.model.User;

/**
 * 用于论坛主页文章的显示
 * Created by acer on 2017/7/11.
 */
public class BBSIndexPostsQueryVo extends Post{

    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    private User user;


    @Override
    public String toString() {
        return "BBSIndexPostsQueryVo{" +
                "post=" + post +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
