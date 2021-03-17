package com.event.dto;

import javax.persistence.Column;

public class EventDto {
	private int id;
	private String address;
	private String banner;
	private String content;
	private String createdAt;
	private String description;
	private String startdate;
	private String enddate;
	private String starttime;
	private String title;
//	private long price;
//	private long paid;
	private int status;
//	private int viewer;
	private int serviceId;
	private String nameService;
	private int userId;
	private String nameUser;
//	private int customerId;
//	private String nameCustomer;
	public EventDto() {
		// TODO Auto-generated constructor stub
	}
	
	public EventDto(int id, String address, String banner, String content, String createdAt, String description,
			String startdate, String enddate,String starttime, String title, 
//			long price, long paid,
			int status, 
//			int viewer,
			int serviceId, int userId
//			,int customerId
			) {
		super();
		this.id = id;
		this.address = address;
		this.banner = banner;
		this.content = content;
		this.createdAt = createdAt;
		this.description = description;
		this.startdate = startdate;
		this.enddate = enddate;
		this.starttime = starttime;
		this.title = title;
//		this.price = price;
//		this.paid = paid;
		this.status = status;
//		this.viewer = viewer;
		this.serviceId = serviceId;
		this.userId = userId;
//		this.customerId = customerId;
	}
	
	public EventDto(int id, String address, String banner, String content, String createdAt, String description,
			String startdate, String enddate,String starttime, String title, 
//			long price, long paid, 
			int status, 
//			int viewer,
			int serviceId, String nameService, int userId, String nameUser
//			, int customerId, String nameCustomer
			) {
		super();
		this.id = id;
		this.address = address;
		this.banner = banner;
		this.content = content;
		this.createdAt = createdAt;
		this.description = description;
		this.startdate = startdate;
		this.enddate = enddate;
		this.starttime = starttime;
		this.title = title;
//		this.price = price;
//		this.paid = paid;
		this.status = status;
//		this.viewer = viewer;
		this.serviceId = serviceId;
		this.nameService = nameService;
		this.userId = userId;
		this.nameUser = nameUser;
//		this.customerId = customerId;
//		this.nameCustomer = nameCustomer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
//	public long getPrice() {
//		return price;
//	}
//	public void setPrice(long price) {
//		this.price = price;
//	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
//	public int getViewer() {
//		return viewer;
//	}
//	public void setViewer(int viewer) {
//		this.viewer = viewer;
//	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getNameService() {
		return nameService;
	}
	public void setNameService(String nameService) {
		this.nameService = nameService;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
//	public int getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerId(int customerId) {
//		this.customerId = customerId;
//	}
//	public String getNameCustomer() {
//		return nameCustomer;
//	}
//	public void setNameCustomer(String nameCustomer) {
//		this.nameCustomer = nameCustomer;
//	}
//	public long getPaid() {
//		return paid;
//	}
//	public void setPaid(long paid) {
//		this.paid = paid;
//	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	
}
