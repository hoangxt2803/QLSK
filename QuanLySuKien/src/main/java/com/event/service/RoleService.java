package com.event.service;

import java.util.List;

import com.event.common.ServiceInfo;
import com.event.dto.RoleDto;

public interface RoleService {
	List<RoleDto> getAll();
	ServiceInfo add(RoleDto dto);
	boolean delete(int id);
	RoleDto findById(int id);
	ServiceInfo update(int id, RoleDto dto);
	RoleDto findByName(String name);
}