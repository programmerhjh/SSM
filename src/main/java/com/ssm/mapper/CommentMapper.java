package com.ssm.mapper;

import com.ssm.model.Comment;
import com.ssm.model.CommentExample;
import java.util.List;

import com.ssm.modelCustom.CommentCustom;
import com.ssm.vo.CommentAndReplyVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 评论dao层
 */
@Repository
public interface CommentMapper extends Mapper<Comment>{

    void deleteCommentList(List<Integer> list);

    void deleteComment(int id);

    /**
     * 返回一个带Comment包装类的list
     * @return
     * @time 2017年8月2日9:03:40
     */
    List<CommentCustom> selectCommentAndPostAndUser();

    List<Comment> selectPostComments(Integer postId);

    /**
     * 在帖子中添加评论
     * @param userId
     * @param postId
     * @param commentText
     * @time 2017年8月2日9:04:17
     */
    void addCommentForPost(@Param("userId") int userId,@Param("postId") int postId,@Param("commentText") String commentText);

    /**
     * 返回用于页面显示的Vo的一个list
     * @param postId
     * @return
     * @time 2017年8月3日9:05:23
     */
    List<CommentAndReplyVo> selectPostComment(Integer postId);

    int countByCommentExample(CommentExample example);

    int deleteByCommentExample(CommentExample example);

    int deleteByCommentPrimaryKey(Integer commentId);

    int insertComment(Comment record);

    int insertCommentSelective(Comment record);

    List<Comment> selectByCommentExampleWithBLOBs(CommentExample example);

    List<Comment> selectByCommentExample(CommentExample example);

    Comment selectByCommentPrimaryKey(Integer commentId);

    int updateByCommentExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByCommentExampleWithBLOBs(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByCommentExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByCommentPrimaryKeySelective(Comment record);

    int updateByCommentPrimaryKeyWithBLOBs(Comment record);

    int updateByCommentPrimaryKey(Comment record);
}