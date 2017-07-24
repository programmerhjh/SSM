package com.ssm.service.impl;

import com.ssm.mapper.CommentMapper;
import com.ssm.mapper.PostMapper;
import com.ssm.mapper.ReplyMapper;
import com.ssm.model.Post;
import com.ssm.service.PostService;
import com.ssm.vo.BBSIndexPostsQueryVo;
import com.ssm.vo.CommentAndReplyVo;
import com.ssm.vo.PostSpecificVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by acer on 2017/7/22.
 */
@Transactional
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private ReplyMapper replyMapper;

    @Resource
    private PostMapper postMapper;


    public PostSpecificVo getPostSpecific(Integer postId) {

        PostSpecificVo postSpecificVo = postMapper.getPostSpecific(postId);
        List<CommentAndReplyVo> temp = commentMapper.selectPostComment(postId);
        for (CommentAndReplyVo commentAndReplyVo : temp){
            commentAndReplyVo.setReplys(replyMapper.getReplyList(commentAndReplyVo.getCommentId()));
        }
        postSpecificVo.setCommentAndReplyVos(temp);

        return postSpecificVo;
    }

    public List<BBSIndexPostsQueryVo> queryHotPost() {
        return postMapper.selectHotArticle();
    }
    public List<BBSIndexPostsQueryVo> queryLastPost() {
        return postMapper.selectLastArticle();
    }

    public void addClickTime(String username,String postname) {
        postMapper.addClickTime(username,postname);
    }

    public void insertPost(Post post) {
        postMapper.insertPost(post);
    }
}
