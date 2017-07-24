package com.ssm.mapper;

import com.ssm.model.Post;
import com.ssm.model.PostCustom;
import com.ssm.model.PostExample;
import java.util.List;

import com.ssm.vo.BBSIndexPostsQueryVo;
import com.ssm.vo.PostSpecificVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PostMapper extends Mapper<Post> {

    PostSpecificVo getPostSpecific(Integer postId);

    void addClickTime(@Param("username") String username,@Param("postname") String postname);

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