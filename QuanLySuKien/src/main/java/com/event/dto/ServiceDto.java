package com.event.dto;

public class ServiceDto {
	private int id;
	private String name;
	private String description;
	private String content;
	private String banner;
	public ServiceDto() {
		// TODO Auto-generated constructor stub
	}
	public ServiceDto(int id, String name, String description, String content, String banner) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.content = content;
		this.banner = banner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	
}
