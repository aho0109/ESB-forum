package com.esb.forumdemo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "COMMENT")
@SQLDelete(sql = "UPDATE COMMENT SET isDeleted = 1 WHERE COMMENTID = ?") //@SQLDelete註解用來覆寫delete指令，每次執行delete指令時，會將其轉換成UPDATE語句，這條指令將isDeleted欄位改為true，而不是永久刪除資料。
@Where(clause = "isDeleted = false")
public class Comments {
	
	@Id
	@Column(name = "COMMENTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentID;
	
	@Column(name = "CONTENT")
	private String commentContent;
	
	@Column(name = "USERID")
	private Integer forummemberID;
	
	@Column(name = "TARGETTYPE")
	private String targetType;
	
	@Column(name = "POSTID")
	private Integer postID;
	
	@Column(name = "REPLYID")
	private Integer replyID;
	
	@Column(name = "CREATEDAT")
	private LocalDateTime commentTime;
	
	@Column(name = "ISDELETED")
	private boolean isDeleted = Boolean.FALSE;
	
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name = "USERID", referencedColumnName = "USERID" , insertable = false, updatable = false)
	private Forummembers forummembers;
	
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name = "POSTID", insertable = false, updatable = false)
	private Posts posts;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "REPLYID", insertable = false, updatable = false)
	private Replies replies;

	/*建構子
	 * 多對多
	 * 多對一
	 * 一對多*/
	
	public Comments() {}

	public Integer getCommentID() {
		return commentID;
	}

	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getForummemberID() {
		return forummemberID;
	}

	public void setForummemberID(Integer forummemberID) {
		this.forummemberID = forummemberID;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public Integer getReplyID() {
		return replyID;
	}

	public void setReplyID(Integer replyID) {
		this.replyID = replyID;
	}

	public LocalDateTime getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Forummembers getForummembers() {
		return forummembers;
	}

	public void setForummembers(Forummembers forummembers) {
		this.forummembers = forummembers;
	}

	public Posts getPosts() {
		return posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

	public Replies getReplies() {
		return replies;
	}

	public void setReplies(Replies replies) {
		this.replies = replies;
	}

	
	
}
