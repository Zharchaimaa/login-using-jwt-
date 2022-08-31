package com.example.login.response;

import java.util.List;

import com.example.login.model.User;



public class UserResponse {
	private String message;
	private List<User> userList;
	private User user;
	public UserResponse() {
		
	}
	public UserResponse(String message, List<User> userList) {
		this.message=message;
		this.userList=userList;
	}
	
	public UserResponse(String message, User user) {
		this.message=message;
		this.user=user;	
	}
	public UserResponse(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getuser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
