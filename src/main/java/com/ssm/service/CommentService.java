package com.ssm.service;

/**
 * Created by acer on 2017/7/22.
 */
public interface CommentService {

    void addCommentForPost(int userId,int postId,String commentText);
}
