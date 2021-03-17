package com.event.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 				//Khai báo cho Hibernate biết lớp này sẽ dùng để ánh xạ đến 1 bảng trong csdl
@Table(name="roles") 	// khai báo cho Hibernate biết lớp này sẽ ánh xạ đến bảng roles trong csdl

public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") // thuộc tính này ánh xạ đến cột id của bảng roles
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)  //mapBy: Chỉ ra tên trường thực thể kết hợp
	private List<User> users;
	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Role(int id, String name, String description, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.users = users;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
