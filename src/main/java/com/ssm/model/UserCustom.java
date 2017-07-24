package com.ssm.model;

import java.util.List;

/**
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
