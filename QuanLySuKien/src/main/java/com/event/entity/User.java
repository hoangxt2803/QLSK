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
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String fullname;
	private String password;
	private String avatar;
	private String phone;
	private String address;
	private String birthday;
	private boolean gender;
	@Column(name = "role_id",nullable = true)
	private int roleId;
	
	@ManyToOne
	@JoinColumn(name = "role_id",insertable =false, updatable =false,nullable = true)
	private Role role;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)  //mapBy: Chỉ ra tên trường thực thể kết hợp
	private List<Event> events;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, String email, String fullname, String password, String avatar, String phone, String address,
			String birthday, boolean gender, int roleId, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.birthday = birthday;
		this.gender = gender;
		this.roleId = roleId;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
