package com.esb.forumdemo.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "[User]")
@Component
public class Forummembers implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer forummemberID;
	
	@Column(name = "USERNAME")
	private String userName;
	
	@Column(name = "USERNICKNAME")
	private String fnickname;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONENUMBER")
	private String phoneNumber;
	
	@Column(name = "PASSWORD")
	private String fpassword;
	
	@OneToMany(mappedBy = "forummembers", cascade = CascadeType.ALL)
	private List<Posts> posts;
	
	@OneToMany(mappedBy = "forummembers", cascade = CascadeType.ALL)
	private List<Replies> replies;
	
	@OneToMany(mappedBy = "forummembers", cascade = CascadeType.ALL)
	private List<Comments> comments;
	

	/*建構子
	 * 多對多
	 * 多對一
	 * 一對多*/
	
	public Forummembers() {}
	
	public Forummembers(String userName, String fnickname, String email, String phoneNumber, String fpassword,
			List<Posts> posts, List<Replies> replies, List<Comments> comments) {
		super();
		this.userName = userName;
		this.fnickname = fnickname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.fpassword = fpassword;
		this.posts = posts;
		this.replies = replies;
		this.comments = comments;
	}
    

	public Integer getForummemberID() {
		return forummemberID;
	}

	public void setForummemberID(Integer forummemberID) {
		this.forummemberID = forummemberID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFnickname() {
		return fnickname;
	}

	public void setFnickname(String fnickname) {
		this.fnickname = fnickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFpassword() {
		return fpassword;
	}

	public void setFpassword(String fpassword) {
		this.fpassword = fpassword;
	}

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
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

	// UserDetails所需
	
	
	@Override
	public String getPassword() {
	    return this.fpassword; // 返回用戶的密碼
	}

	@Override
	public String getUsername() {
	    return this.userName; // 返回用戶的用戶名
	}

	@Override
	public boolean isAccountNonExpired() {
	    return true; // 帳戶是否過期，這裡設置為永不過期
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true; // 帳戶是否鎖定，這裡設置為永不鎖定
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return true; // 憑證（密碼）是否過期，這裡設置為永不過期
	}

	@Override
	public boolean isEnabled() {
	    return true; // 帳戶是否啟用，這裡設置為啟用
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return Collections.emptyList(); // 返回用戶的授權，這裡先設置為空列表

	}

    
    
	

	

	
	
	
	
	
	
	
	
	
}
