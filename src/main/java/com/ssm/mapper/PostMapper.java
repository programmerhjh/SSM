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

@Repository
public interface PostMapper extends Mapper<Post> {

    void deletePostList(List<Integer> list);

    void deletePost(int id);

    //用于Excel表对数据库的批量插入
    void insertPostList(List<Post> list);

    List<PostCustom> selectPostAndUser();

    List<PostArticleCustom> selectUserPostList(@Param("userId") int userId,RowBounds rowBounds);

    List<PostArticleCustom> selectAllPostList(RowBounds rowBounds);

    List<PostArticleCustom> searchPostData(@Param("data") String data,RowBounds rowBounds);

    PostSpecificVo getPostSpecific(Integer postId);

    void addClickTime(@Param("username") String username,@Param("postname") String postname,@Param("flag") boolean flag);

    int countByPostExample(PostExample example);

    int deleteByPostExample(PostExample example);

    int deleteByPostPrimaryKey(Integer postId);

    int insertPost(Post record);

    int insertPostSelective(Post record);

    List<BBSIndexPostsQueryVo> selectHotArticle();

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