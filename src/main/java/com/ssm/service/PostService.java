package com.ssm.service;

import com.ssm.model.Post;
import com.ssm.vo.BBSIndexPostsQueryVo;
import com.ssm.vo.PostSpecificVo;

import java.util.List;

/**
 * Created by acer on 2017/7/22.
 */
public interface PostService {

    PostSpecificVo getPostSpecific(Integer postId);

    //热门文章
    List<BBSIndexPostsQueryVo> queryHotPost();
    //最新帖子
    List<BBSIndexPostsQueryVo> queryLastPost();

    void addClickTime(String username,String postname);

    //插入一则帖子
    void insertPost(Post post);

}
