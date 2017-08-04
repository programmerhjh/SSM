package com.ssm.model;

import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Table(name = "post")
public class Post {
    private Integer postId;

    private String postName;

    private Date postCreatetime;

    private Integer postAuthor;

    private Integer postClicktimes;

    private String postCategory;

    private String postPost;

    public Post(){}

    public Post(String postName, Integer postAuthor, Integer postClicktimes, String postCategory,String postPost) {
        this.postName = postName;
        this.postAuthor = postAuthor;
        this.postClicktimes = postClicktimes;
        this.postCategory = postCategory;
        this.postPost = postPost;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public Date getPostCreatetime() throws ParseException {
        return postCreatetime;
    }

    public void setPostCreatetime(Date postCreatetime){
        this.postCreatetime = postCreatetime;
    }

    public Integer getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(Integer postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Integer getPostClicktimes() {
        return postClicktimes;
    }

    public void setPostClicktimes(Integer postClicktimes) {
        this.postClicktimes = postClicktimes;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory == null ? null : postCategory.trim();
    }

    public String getPostPost() {
        return postPost;
    }

    public void setPostPost(String postPost) {
        this.postPost = postPost == null ? null : postPost.trim();
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postName='" + postName + '\'' +
                ", postCreatetime=" + postCreatetime +
                ", postAuthor=" + postAuthor +
                ", postClicktimes=" + postClicktimes +
                ", postCategory='" + postCategory + '\'' +
                ", postPost='" + postPost + '\'' +
                '}';
    }
}