package com.ssm.modelCustom;

import com.ssm.model.Post;
import com.ssm.model.Reply;

/**
 * 帖子回复包装类
 * Created by acer on 2017/7/27.
 */
public class ReplyPostCustom extends Reply{

    private Post post;

    public Post getPost() {
        return post;
    }

    @Override
    public String toString() {
        return "ReplyPostCustom{" +
                "post=" + post +
                '}';
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
