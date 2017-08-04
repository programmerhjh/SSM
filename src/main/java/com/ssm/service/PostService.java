package com.ssm.service;

import com.ssm.model.Comment;
import com.ssm.model.Post;
import com.ssm.modelCustom.PostArticleCustom;
import com.ssm.modelCustom.PostCustom;
import com.ssm.vo.BBSIndexPostsQueryVo;
import com.ssm.vo.PostSpecificVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by acer on 2017/7/22.
 */
public interface PostService {

    void deletePostList(List<Integer> list);

    void deletePost(int id);

    void insertPostList(List<Post> list);

    List<Post> getAllPost();

    List<PostCustom> selectPostAndUser();

    List<PostArticleCustom> searchPostData(String data,RowBounds rowBounds);

    List<Comment> selectPostComments(Integer postId);

    PostSpecificVo getPostSpecific(Integer postId);

    List<PostArticleCustom> selectAllPostList(RowBounds rowBounds);

    List<PostArticleCustom> selectUserPostList(int userId,RowBounds rowBounds);

    //热门文章
    List<BBSIndexPostsQueryVo> queryHotPost();
    //最新帖子
    List<BBSIndexPostsQueryVo> queryLastPost();

    void addClickTime(String username,String postname);

    //插入一则帖子
    void insertPost(Post post);

}
