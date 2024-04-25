package com.esb.forumdemo.controller;

public class LoginDto {
	
    private String userName;
    private String phoneNumber;
	private String fpassword;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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

    

}
