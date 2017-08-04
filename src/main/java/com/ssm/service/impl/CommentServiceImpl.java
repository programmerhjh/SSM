package com.ssm.service.impl;

import com.ssm.mapper.CommentMapper;
import com.ssm.modelCustom.CommentCustom;
import com.ssm.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by acer on 2017/7/22.
 */
@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;


    public void deleteCommentList(List<Integer> list) {
        commentMapper.deleteCommentList(list);
    }

    public void deleteComment(int id) {
        commentMapper.deleteComment(id);
    }

    public List<CommentCustom> selectCommentAndPostAndUser() {
        return commentMapper.selectCommentAndPostAndUser();
    }

    public void addCommentForPost(int userId, int postId, String commentText) {
        commentMapper.addCommentForPost(userId,postId,commentText);
    }
}
