package com.event.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String address;
	private String banner;
	private String content;
	@Column(name = "created_at")
	private String createdAt;
	private String description;
	private String startdate;
	private String enddate;
	private String starttime;
	private String title;
	//private long price;
	//private long paid;
	private int status;
	//private int viewer;
	
	@Column(name = "service_id")
	private int serviceId;
	@Column(name = "user_id")
	private int userId;
//	@Column(name = "customer_id")
//	private int customerId;
	
//	@ManyToOne
//	@JoinColumn(name = "customer_id",insertable =false, updatable =false,nullable = true)
//	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "service_id",insertable =false, updatable =false,nullable = true)
	private Service service;
	
	@ManyToOne
	@JoinColumn(name = "user_id",insertable =false, updatable =false,nullable = true)
	private User user;
	
//	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY)  //mapBy: Chỉ ra tên trường thực thể kết hợp
//	private List<Payment> payment;
	
	public Event() {
		// TODO Auto-generated constructor stub
	}


	public Event(int id, String address, String banner, String content, String createdAt, String description,
			String startdate, String enddate,String starttime, String title, 
//			long price, long paid, 
			int status,
//			int viewer,
			int serviceId, int userId
//			, int customerId
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
		//this.price = price;
		//this.paid = paid;
		this.status = status;
		//this.viewer = viewer;
		this.serviceId = serviceId;
		this.userId = userId;
		//this.customerId = customerId;
	}

	
	public Event(int id, String address, String banner, String content, String createdAt, String description,
			String startdate, String enddate,String starttime, String title,
//			long price, long paid, 
			int status, 
//			int viewer,
			int serviceId, int userId,
//			int customerId, Customer customer,
			Service service, User user) {
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
//		this.customer = customer;
		this.service = service;
		this.user = user;
	}


//	public Event(int id, String address, String banner, String content, String createdAt, String description,
//			String startdate, String enddate,String starttime, String title,
////			long price, long paid, 
//			int status, 
////			int viewer,
//			int serviceId, int userId, 
////			int customerId, Customer customer, 
//			Service service, User user,
//			List<Payment> payment
//			) {
//		super();
//		this.id = id;
//		this.address = address;
//		this.banner = banner;
//		this.content = content;
//		this.createdAt = createdAt;
//		this.description = description;
//		this.startdate = startdate;
//		this.enddate = enddate;
//		this.starttime = starttime;
//		this.title = title;
////		this.price = price;
////		this.paid = paid;
//		this.status = status;
////		this.viewer = viewer;
//		this.serviceId = serviceId;
//		this.userId = userId;
////		this.customerId = customerId;
////		this.customer = customer;
//		this.service = service;
//		this.user = user;
////		this.payment = payment;
//	}




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

	
//	public long getPrice() {
//		return price;
//	}
//
//	public void setPrice(long price) {
//		this.price = price;
//	}
//
//	public int getViewer() {
//		return viewer;
//	}
//
//	public void setViewer(int viewer) {
//		this.viewer = viewer;
//	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

//	public int getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(int customerId) {
//		this.customerId = customerId;
//	}
//
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	


//	public List<Payment> getPayment() {
//		return payment;
//	}
//
//
//
//
//	public void setPayment(List<Payment> payment) {
//		this.payment = payment;
//	}




	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}




//	public long getPaid() {
//		return paid;
//	}
//
//
//
//
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
