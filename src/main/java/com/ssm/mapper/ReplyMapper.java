package com.ssm.mapper;

import com.ssm.model.Reply;
import com.ssm.model.ReplyCustom;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by acer on 2017/7/24.
 */
@Repository
public interface ReplyMapper extends Mapper<Reply> {

    //返回对应的commentId的回复集合
    List<ReplyCustom> getReplyList(Integer commentId);

}
