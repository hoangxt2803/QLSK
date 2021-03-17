package com.event.dto;


import com.event.entity.Role;

public class UserDto {
	private int id;
	private String email;
	private String fullname;
	private String password;
	private String avatar;
	private String phone;
	private String address;
	private String birthday;
	private boolean gender;
	private int roleId;
	private Role role;
	public UserDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public UserDto(int id, String email, String fullname, String password, String avatar, String phone, String address,
			String birthday, boolean gender, int roleId) {
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
	}

	public UserDto(int id, String email, String fullname, String avatar, String phone, String address, String birthday,
			boolean gender, int roleId) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.birthday = birthday;
		this.gender = gender;
		this.roleId = roleId;
	}

	public UserDto(int id, String email, String fullname, String password, String avatar, String phone, String address,
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
