package com.ssm.mapper;

import com.ssm.model.Post;
import com.ssm.model.PostExample;
import com.ssm.modelCustom.PostArticleCustom;
import com.ssm.modelCustom.PostCustom;
import com.ssm.vo.BBSIndexPostsQueryVo;
import com.ssm.vo.PostSpecificVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 帖子dao层
 */
@Repository
public interface PostMapper extends Mapper<Post> {

    /**
     * 根据list里面的post_id来批量删除
     * @param list
     * @time 2017年8月2日9:06:19
     */
    void deletePostList(List<Integer> list);

    void deletePost(int id);

    //用于Excel表对数据库的批量插入
    void insertPostList(List<Post> list);

    /**
     * 返回一个装有帖子包装类的一个list
     * @return
     * @time 2017年8月4日9:07:06
     */
    List<PostCustom> selectPostAndUser();

    /**
     * 返回一个根据用户id装有帖子包装类的一个分页list
     * @param userId
     * @param rowBounds
     * @return
     * @time 2017年8月3日9:07:51
     */
    List<PostArticleCustom> selectUserPostList(@Param("userId") int userId,RowBounds rowBounds);

    /**
     * 返回一个帖子包装类的分页list
     * @param rowBounds
     * @return
     * @time 2017年8月4日9:08:43
     */
    List<PostArticleCustom> selectAllPostList(RowBounds rowBounds);

    /**
     * 根据搜索内容返回一个装有帖子包装类的分页list
     * @param data
     * @param rowBounds
     * @return
     * @time 2017年8月5日9:10:35
     */
    List<PostArticleCustom> searchPostData(@Param("data") String data,RowBounds rowBounds);

    /**
     * 获取帖子详情，返回一个用于显示的一个帖子详情Vo类
     * @param postId
     * @return
     * @time 2017年8月4日9:10:27
     */
    PostSpecificVo getPostSpecific(Integer postId);

    /**
     * 通过用户名和帖子标题来添加点赞次数
     * @param username
     * @param postname
     * @param flag
     * @time 2017年7月25日9:11:03
     */
    void addClickTime(@Param("username") String username,@Param("postname") String postname,@Param("flag") boolean flag);

    int countByPostExample(PostExample example);

    int deleteByPostExample(PostExample example);

    int deleteByPostPrimaryKey(Integer postId);

    int insertPost(Post record);

    int insertPostSelective(Post record);

    /**
     * 返回一个用于显示热门帖子的装有帖子包装类的list
     * @return
     * @time 2017年8月1日9:12:16
     *
     */
    List<BBSIndexPostsQueryVo> selectHotArticle();

    /**
     * 返回一个用于显示最新发表帖子的装有帖子包装类的list
     * @return
     * @time 2017年8月1日9:13:31
     */
    List<BBSIndexPostsQueryVo> selectLastArticle();

    List<Post> selectByPostExampleWithBLOBs(PostExample example);

    List<Post> selectByPostExample(PostExample example);

    Post selectByPostPrimaryKey(Integer postId);

    int updateByPostExampleSelective(@Param("record") Post record, @Param("example") PostExample example);

    int updateByPostExampleWithBLOBs(@Param("record") Post record, @Param("example") PostExample example);

    int updateByPostExample(@Param("record") Post record, @Param("example") PostExample example);

    int updateByPostPrimaryKeySelective(Post record);

    int updateByPostPrimaryKeyWithBLOBs(Post record);

    int updateByPostPrimaryKey(Post record);
}