package com.event.service;

import java.util.List;

import com.event.common.ServiceInfo;
import com.event.dto.ServiceDto;

public interface ServiceService {
	List<ServiceDto> getAll();
	ServiceInfo add(ServiceDto dto);
	boolean delete(int id);
	ServiceInfo update(ServiceDto dto);
	ServiceDto findById(int id);
	int countService();
}
