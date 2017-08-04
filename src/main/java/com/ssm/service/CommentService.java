package com.ssm.service;

import com.ssm.modelCustom.CommentCustom;

import java.util.List;

/**
 * Created by acer on 2017/7/22.
 */
public interface CommentService {

    void deleteCommentList(List<Integer> list);

    void deleteComment(int id);

    List<CommentCustom> selectCommentAndPostAndUser();

    void addCommentForPost(int userId,int postId,String commentText);
}
