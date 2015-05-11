package com.ping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "t_comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = -2796345407909064066L;

    private String commentId;
    private Timestamp commentDate;
    private String imageId;
    private String username;
    private long approve;
    private String content;

    public Comment() {
    }

    @Id
    @Column(name = "comment_id")
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @Column(name = "comment_date")
    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    @Column(name = "comment_approve")
    public long getApprove() {
        return approve;
    }

    public void setApprove(long approve) {
        this.approve = approve;
    }

    @Column(name = "comment_username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "comment_content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "comment_imageId")
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((commentId == null) ? 0 : commentId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comment other = (Comment) obj;
        if (commentId == null) {
            if (other.commentId != null)
                return false;
        } else if (!commentId.equals(other.commentId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Comment [commentId=" + commentId + ", commentDate="
                + commentDate + ", imageId=" + imageId + ", username="
                + username + ", approve=" + approve + ", content=" + content
                + "]";
    }

}
