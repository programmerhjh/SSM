package com.ssm.modelCustom;

import com.ssm.model.Comment;
import com.ssm.model.Post;
import com.ssm.model.User;

import java.util.List;

/**
 * 帖子包装类
 * Created by acer on 2017/7/19.
 */
public class PostCustom extends Post {
    private User user;
    private List<Comment> comments;

    public PostCustom(){}

    public PostCustom(String postName, Integer postAuthor, Integer postClicktimes, String postCategory,String postPost) {
        super(postName, postAuthor, postClicktimes, postCategory,postPost);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "PostCustom{" +
                "user=" + user +
                ", comments=" + comments +
                '}';
    }
}
