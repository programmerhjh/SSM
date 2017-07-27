package com.ssm.service.impl;

import com.ssm.mapper.ReplyMapper;
import com.ssm.model.Reply;
import com.ssm.modelCustom.ReplyCustom;
import com.ssm.modelCustom.ReplyPostCustom;
import com.ssm.service.ReplyService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by acer on 2017/7/25.
 */

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Resource
    private ReplyMapper replyMapper;

    public Reply selectByReplyPrimaryKey(int replyId){
        return replyMapper.selectByReplyPrimaryKey(replyId);
    }

    public void addReplyForComment(int userId, int postId, int commentId, String replyReply) {
        replyMapper.addReplyForComment(userId,postId,commentId,replyReply);
    }

    public List<ReplyPostCustom> getReplyPostList(int userId, RowBounds rowBounds) {
        List<ReplyPostCustom> list = replyMapper.getReplyPostList(userId,rowBounds);
        for(ReplyPostCustom replyPostCustom:list){
            System.out.println(replyPostCustom);
        }
        return replyMapper.getReplyPostList(userId,rowBounds);
    }

    public ReplyCustom getReplyInstance(){
        return replyMapper.getReplyInstance();
    }

}
