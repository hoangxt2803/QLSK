package com.event.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="services")
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String content;
	private String banner;
	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY)  //mapBy: Chỉ ra tên trường thực thể kết hợp
	private List<Event> events;
	
	public Service() {
		// TODO Auto-generated constructor stub
	}
	
	public Service(int id, String name, String description, String content, String banner) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.content = content;
		this.banner = banner;
	}

	public Service(int id, String name, String description, String content, String banner, List<Event> events) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.content = content;
		this.banner = banner;
		this.events = events;
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

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}
