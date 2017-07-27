package com.ssm.model;

import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "comment")
public class Comment {
    private Integer commentId;

    private Integer commentUser;

    private Timestamp commentCreatetime;

    private Integer commentPost;

    private String commentComment;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(Integer commentUser) {
        this.commentUser = commentUser;
    }

    public Date getCommentCreatetime() {
        return commentCreatetime;
    }

    public void setCommentCreatetime(Timestamp commentCreatetime) {
        this.commentCreatetime = commentCreatetime;
    }

    public Integer getCommentPost() {
        return commentPost;
    }

    public void setCommentPost(Integer commentPost) {
        this.commentPost = commentPost;
    }

    public String getCommentComment() {
        return commentComment;
    }

    public void setCommentComment(String commentComment) {
        this.commentComment = commentComment == null ? null : commentComment.trim();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentUser=" + commentUser +
                ", commentCreatetime=" + commentCreatetime +
                ", commentPost=" + commentPost +
                ", commentComment='" + commentComment + '\'' +
                '}';
    }
}