package com.ssm.model;

import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by acer on 2017/7/24.
 */
@Table(name = "reply")
public class Reply {

    private String replyReply;

    private Integer replyId;

    private Timestamp replyCreatetime;

    public Timestamp getReplyCreatetime() {
        return replyCreatetime;
    }

    public void setReplyCreatetime(Timestamp replyCreatetime) {
        this.replyCreatetime = replyCreatetime;
    }

    public String getReplyReply() {
        return replyReply;
    }

    public void setReplyReply(String replyReply) {
        this.replyReply = replyReply;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyReply='" + replyReply + '\'' +
                ", replyId=" + replyId +
                ", replyCreatetime=" + replyCreatetime +
                '}';
    }
}
