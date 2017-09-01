package com.ssm.service;

import com.ssm.model.Reply;
import com.ssm.modelCustom.ReplyCustom;
import com.ssm.modelCustom.ReplyPostCustom;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 回复Service层
 * Created by acer on 2017/7/25.
 */
public interface ReplyService {

    ReplyCustom getReplyInstance();

    Reply selectByReplyPrimaryKey(int replyId);

    void addReplyForComment(int userId,int postId,int commentId,String replyReply);

    List<ReplyPostCustom> getReplyPostList(int userId, RowBounds rowBounds);
}
