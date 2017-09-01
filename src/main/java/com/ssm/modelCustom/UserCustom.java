package com.ssm.modelCustom;

import com.ssm.model.Comment;
import com.ssm.model.Post;
import com.ssm.model.User;

import java.util.List;

/**
 * 用户包装类
 * Created by acer on 2017/7/19.
 */
public class UserCustom extends User {
    private List<Post> posts;
    private List<Comment> comments;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
