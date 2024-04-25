package com.esb.forumdemo.controller;

public class RepliesDto {
	
	private String replyContent;
	private Integer sboardID;
	private String fnickname;
	
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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
