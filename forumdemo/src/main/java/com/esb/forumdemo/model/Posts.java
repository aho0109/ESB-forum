package com.esb.forumdemo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

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

@SuppressWarnings("deprecation") //告訴spring忽視警告
@Entity
@Table(name = "POST")
@Component
@SQLDelete(sql = "UPDATE POST SET isDeleted = 1 WHERE POSTID = ?") //@SQLDelete註解用來覆寫delete指令，每次執行delete指令時，會將其轉換成UPDATE語句，這條指令將isDeleted欄位改為true，而不是永久刪除資料。
@Where(clause = "isDeleted = false") //@where註解將會提供一個過濾器，當我們需要讀取Product資料時，結果中不會包含is_deleted = true的資料。在資料庫中false是0，true是1，但回傳是false和true
public class Posts {
	
	@Id
	@Column(name = "POSTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postID;
	
	@Column(name = "POSTTITLE")
	private String postTitle;
	
	@Column(name = "CONTENT")
	private String postContent;
	
	@Column(name = "USERID")
	private Integer forummemberID;
	
	@Column(name = "SBOARDID") // 加了"insertable = false, updatable = false"這裡才能存在
	private Integer sboardID;
	
	@Column(name = "CREATEDAT")
	private LocalDateTime postTime;

	@Column(name = "ISDELETED")
	private boolean isDeleted = Boolean.FALSE;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USERID", referencedColumnName = "USERID" , insertable = false, updatable = false)
	private Forummembers forummembers;

	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "SBOARDID", insertable = false, updatable = false) //加了這行就能和sboardID實例共存
	private Sboards sboards;
	
	@OneToMany(mappedBy = "posts",cascade = CascadeType.ALL)
	private List<Replies> replies;
	
	@OneToMany(mappedBy = "posts",cascade = CascadeType.ALL)
	private List<Comments> comments;
	
	/*建構子
	 * 多對多
	 * 多對一
	 * 一對多*/
	
	public Posts() {}
	

	public Posts(Integer postID, String postTitle, String postContent, Integer forummemberID, Integer sboardID,
			LocalDateTime postTime, boolean isDeleted, Forummembers forummembers, Sboards sboards,
			List<Replies> replies, List<Comments> comments) {
		super();
		this.postID = postID;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.forummemberID = forummemberID;
		this.sboardID = sboardID;
		this.postTime = postTime;
		this.isDeleted = isDeleted;
		this.forummembers = forummembers;
		this.sboards = sboards;
		this.replies = replies;
		this.comments = comments;
	}


	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Integer getForummemberID() {
		return forummemberID;
	}

	public void setForummemberID(Integer forummemberID) {
		this.forummemberID = forummemberID;
	}

	public Integer getSboardID() {
		return sboardID;
	}

	public void setSboardID(Integer sboardID) {
		this.sboardID = sboardID;
	}

	public LocalDateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
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

	public Sboards getSboards() {
		return sboards;
	}

	public void setSboards(Sboards sboards) {
		this.sboards = sboards;
	}

	public List<Replies> getReplies() {
		return replies;
	}

	public void setReplies(List<Replies> replies) {
		this.replies = replies;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	
}
