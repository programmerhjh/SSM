package com.ssm.mapper;

import com.ssm.model.Reply;
import com.ssm.modelCustom.ReplyCustom;
import com.ssm.modelCustom.ReplyPostCustom;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 回复的dao层
 * Created by acer on 2017/7/24.
 */
@Repository
public interface ReplyMapper extends Mapper<Reply> {

    /**
     * 根据用户id返回一个装有帖子回复一起的包装类的分页list
     * @param userId
     * @param rowBounds
     * @return
     * @time 2017年8月4日9:15:29
     */
    List<ReplyPostCustom> getReplyPostList(@Param("userId") int userId, RowBounds rowBounds);

    Reply selectByReplyPrimaryKey(int id);

    /**
     * 获得一个回复包装类的对象
     * @return
     * @time 2017年8月5日9:15:17
     */
    ReplyCustom getReplyInstance();

    //插入一条回复
    void addReplyForComment(@Param("userId") int userId,@Param("postId") int postId,@Param("commentId") int commentId,@Param("replyReply") String replyReply);

    //返回对应的commentId的回复集合
    List<ReplyCustom> getReplyList(Integer commentId);

}
