package com.esb.forumdemo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "REPLIES")
@SQLDelete(sql = "UPDATE REPLIES SET isDeleted = 1 WHERE REPLYID = ?") //@SQLDelete註解用來覆寫delete指令，每次執行delete指令時，會將其轉換成UPDATE語句，這條指令將isDeleted欄位改為true，而不是永久刪除資料。
@Where(clause = "isDeleted = false")
public class Replies {

	@Id
	@Column(name = "REPLYID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer replyID;
	
	@Column(name = "REPLYCONTENT")
	private String replyContent;
	
	@Column(name = "USERID")
	private Integer forummemberID;
	
	@Column(name = "POSTID")
	private Integer postID;
	
	@Column(name = "CREATEDAT")
	private LocalDateTime replyTime;
	
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
	
	@OneToMany(mappedBy = "replies",cascade = CascadeType.ALL)
	private List<Comments> comments;
	
	/*建構子
	 * 多對多
	 * 多對一
	 * 一對多*/
	
	public Replies() {}

	public Integer getReplyID() {
		return replyID;
	}

	public void setReplyID(Integer replyID) {
		this.replyID = replyID;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Integer getForummemberID() {
		return forummemberID;
	}

	public void setForummemberID(Integer forummemberID) {
		this.forummemberID = forummemberID;
	}

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public LocalDateTime getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(LocalDateTime replyTime) {
		this.replyTime = replyTime;
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

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}


	
	
}
