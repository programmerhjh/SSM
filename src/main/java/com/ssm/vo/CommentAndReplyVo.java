package com.ssm.vo;

import com.ssm.model.Comment;
import com.ssm.modelCustom.ReplyCustom;
import com.ssm.modelCustom.UserExpand;

import java.util.List;

/**
 * Created by acer on 2017/7/24.
 */
public class CommentAndReplyVo extends Comment {

    @Override
    public String toString() {
        return "CommentAndReplyVo{" +
                "commentUserVo=" + commentUserVo +
                ", replys=" + replys +
                '}';
    }

    public UserExpand getCommentUserVo() {
        return commentUserVo;
    }

    public void setCommentUserVo(UserExpand commentUserVo) {
        this.commentUserVo = commentUserVo;
    }

    private UserExpand commentUserVo;

    private List<ReplyCustom> replys;


    public List<ReplyCustom> getReplys() {
        return replys;
    }

    public void setReplys(List<ReplyCustom> replys) {
        this.replys = replys;
    }

}
