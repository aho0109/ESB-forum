package com.esb.forumdemo.controller;

public class PostsDto {

	private String postTitle;
	private String postContent;
	private Integer sboardID;
	private String fnickname;
	
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
	public Integer getSboardID() {
		return sboardID;
	}
	public void setSboardID(Integer sboardID) {
		this.sboardID = sboardID;
	}
	public String getFnickname() {
		return fnickname;
	}
	public void setFnickname(String fnickname) {
		this.fnickname = fnickname;
	}
	
	

}
