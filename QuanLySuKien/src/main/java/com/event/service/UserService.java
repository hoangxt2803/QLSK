package com.event.service;

import java.util.List;

import com.event.common.ServiceInfo;
import com.event.dto.UserDto;

public interface UserService {
	List<UserDto> getAll();
	ServiceInfo add(UserDto dto);
	boolean delete(int id);
	ServiceInfo update(UserDto dto);
	UserDto findById(int id);
	UserDto findByEmail(String email);
	int countUser();
}
