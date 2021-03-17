package com.event.dto;

public class NewsDto {
	private int id;
	private String title;
	private String banner;
	private String content;
	private int user_id;
	private String userName;
	public NewsDto() {
		// TODO Auto-generated constructor stub
	}
	public NewsDto(int id, String title, String banner, String content, int user_id) {
		super();
		this.id = id;
		this.title = title;
		this.banner = banner;
		this.content = content;
		this.user_id = user_id;
	}
	
	public NewsDto(int id, String title, String banner, String content, int user_id, String userName) {
		super();
		this.id = id;
		this.title = title;
		this.banner = banner;
		this.content = content;
		this.user_id = user_id;
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
