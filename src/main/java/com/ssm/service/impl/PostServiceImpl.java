package com.ssm.service.impl;

import com.ssm.mapper.CommentMapper;
import com.ssm.mapper.PostMapper;
import com.ssm.mapper.ReplyMapper;
import com.ssm.model.Comment;
import com.ssm.model.Post;
import com.ssm.modelCustom.PostArticleCustom;
import com.ssm.modelCustom.PostCustom;
import com.ssm.service.PostService;
import com.ssm.vo.BBSIndexPostsQueryVo;
import com.ssm.vo.CommentAndReplyVo;
import com.ssm.vo.PostSpecificVo;
import org.apache.ibatis.session.RowBounds;
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
    
    public void deletePostList(List<Integer> list) {
        postMapper.deletePostList(list);
    }

    public void deletePost(int id) {
        postMapper.deletePost(id);
    }

    public void insertPostList(List<Post> list) {
        postMapper.insertPostList(list);
    }

    public List<Post> getAllPost() {
        return postMapper.selectAll();
    }

    public List<PostCustom> selectPostAndUser() {
        return postMapper.selectPostAndUser();
    }

    public List<PostArticleCustom> searchPostData(String data, RowBounds rowBounds) {
        List<PostArticleCustom> list = postMapper.searchPostData(data,rowBounds);
        for (PostArticleCustom postArticleCustom:list){
            System.out.println(postArticleCustom);
            System.out.println("--------------------");
            postArticleCustom.setComments(commentMapper.selectPostComments(postArticleCustom.getPostId()));
        }
        return list;
    }

    public List<Comment> selectPostComments(Integer postId) {
        return commentMapper.selectPostComments(postId);
    }

    public PostSpecificVo getPostSpecific(Integer postId) {
        PostSpecificVo postSpecificVo = postMapper.getPostSpecific(postId);
        List<CommentAndReplyVo> temp = commentMapper.selectPostComment(postId);
        for (CommentAndReplyVo commentAndReplyVo : temp){
            commentAndReplyVo.setReplys(replyMapper.getReplyList(commentAndReplyVo.getCommentId()));
        }
        postSpecificVo.setCommentAndReplyVos(temp);
        return postSpecificVo;
    }

    public List<PostArticleCustom> selectAllPostList(RowBounds rowBounds){
        List<PostArticleCustom> postList = postMapper.selectAllPostList(rowBounds);
        for(PostArticleCustom custom: postList){
            custom.setComments(commentMapper.selectPostComments(custom.getPostId()));
        }
        return postList;
    }

    public List<PostArticleCustom> selectUserPostList(int userId, RowBounds rowBounds) {
        return postMapper.selectUserPostList(userId,rowBounds);
    }

    public List<BBSIndexPostsQueryVo> queryHotPost() {
        return postMapper.selectHotArticle();
    }

    public List<BBSIndexPostsQueryVo> queryLastPost() {
        return postMapper.selectLastArticle();
    }

    public void addClickTime(String username,String postname,boolean flag) {
        postMapper.addClickTime(username,postname,flag);
    }

    public void insertPost(Post post) {
        postMapper.insertPost(post);
    }
}
