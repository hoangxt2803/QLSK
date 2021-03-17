package com.event.service;

import java.util.List;

import com.event.common.ServiceInfo;
import com.event.dto.EventDto;

public interface EventService {
	List<EventDto> getAll();
	List<EventDto> getAllByUserId(int id);
	ServiceInfo add(EventDto dto);
	boolean delete(int id);
	ServiceInfo update(EventDto dto);
	EventDto findById(int id);
	int countEvent();
}
