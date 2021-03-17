package com.event.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.common.ServiceInfo;
import com.event.dto.EventDto;
import com.event.dto.UserDto;
//import com.event.entity.Customer;
import com.event.entity.Event;
import com.event.entity.User;
//import com.event.repository.CustomerRepository;
import com.event.repository.EventRepository;
import com.event.repository.ServiceRepository;
import com.event.repository.UserRepository;
import com.event.service.EventService;

@Service
public class EventServiceImpl implements EventService{
	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
//	@Autowired
//	CustomerRepository customerRepository;
	@Autowired
	ServiceRepository serviceRepository;

	private EventDto entityToDto(Event entity) {
		return new EventDto(entity.getId(),
				entity.getAddress(),
				entity.getBanner(),
				entity.getContent(),
				entity.getCreatedAt(),
				entity.getDescription(),
				entity.getStartdate(),
				entity.getEnddate(),
				entity.getStarttime(),
				entity.getTitle(),
//				entity.getPrice(),
//				entity.getPaid(),
				entity.getStatus(),
//				entity.getViewer(),
				entity.getServiceId(),
				entity.getUserId()
//				entity.getCustomerId()
				);
	}
	private Event dtoToEntity(EventDto dto) {
		return new Event(
				dto.getId(),
				dto.getAddress(),
				dto.getBanner(),
				dto.getContent(),
				dto.getCreatedAt(),
				dto.getDescription(),
				dto.getStartdate(),
				dto.getEnddate(),
				dto.getStarttime(),
				dto.getTitle(),
//				dto.getPrice(),
//				dto.getPaid(),
				dto.getStatus(),
//				dto.getViewer(),
				dto.getServiceId(),
				dto.getUserId()
//				dto.getCustomerId()
				);
	}
	@Override
	public List<EventDto> getAll() {
		List<EventDto> dtos = new ArrayList<EventDto>();
		List<Event> events = eventRepository.findAll();
		for(Event event:events)
		{
			EventDto dto = entityToDto(event);
			User user = userRepository.findById(event.getUserId()).get();
			dto.setNameUser(user.getFullname());
//			Customer customer = customerRepository.findById(dto.getCustomerId()).get();
//			dto.setNameCustomer(customer.getName());
			com.event.entity.Service service = serviceRepository.findById(dto.getServiceId()).get();
			dto.setNameService(service.getName());
			dtos.add(dto);
		}
		return dtos;
	}
	@Override
	public List<EventDto> getAllByUserId(int id) {
		List<EventDto> dtos = new ArrayList<EventDto>();
		List<Event> events = eventRepository.findByUserId(id);
		for(Event event:events)
		{
			EventDto dto = entityToDto(event);
			User user = userRepository.findById(event.getUserId()).get();
			dto.setNameUser(user.getFullname());
//			Customer customer = customerRepository.findById(dto.getCustomerId()).get();
//			dto.setNameCustomer(customer.getName());
			com.event.entity.Service service = serviceRepository.findById(dto.getServiceId()).get();
			dto.setNameService(service.getName());
			dtos.add(dto);
		}
		return dtos;
	}
	@Override
	public ServiceInfo add(EventDto dto) {
		try {
			Event event = new Event();
			event = dtoToEntity(dto);
			eventRepository.save(event);
			return new ServiceInfo(true,"Thêm thành công!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceInfo(false,"Thêm thất bại!");
	}

	@Override
	public boolean delete(int id) {
		Optional<Event> optional = eventRepository.findById(id);
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return false;
		Event event = optional.get();
		eventRepository.delete(event);
		return true;
	}

	@Override
	public ServiceInfo update(EventDto dto) {
		Optional<Event> optional = eventRepository.findById(dto.getId());
		if(optional.isPresent()==false)
			return new ServiceInfo(false, "Cập nhật thất bại 1!");
		Event event = optional.get();
//		Customer
//		Optional<Customer> optionalCustomer = customerRepository.findById(dto.getCustomerId());
//		if(optionalCustomer.isPresent()==false)
//			return new ServiceInfo(false, "Cập nhật thất bại 2!");
//		Customer customer = optionalCustomer.get();
//		Service
		Optional<com.event.entity.Service> optionalService = serviceRepository.findById(dto.getServiceId());
		if(optionalService.isPresent()==false)
			return new ServiceInfo(false, "Cập nhật thất bại 3!");
		com.event.entity.Service service = optionalService.get();
//		User - Nhân viên
		Optional<User> optionalUser = userRepository.findById(dto.getUserId());
		System.err.println(dto.getUserId());
		if(optionalUser.isPresent()==false)
			return new ServiceInfo(false, "Cập nhật thất bại 4!");
		User user = optionalUser.get();
		try {
			event = dtoToEntity(dto);
			event.setService(service);
//			event.setCustomer(customer);
			event.setUser(user);
			eventRepository.save(event);
			return new ServiceInfo(true,"Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ServiceInfo(true,"Cập nhật thất bại 5!");
	}

	@Override
	public EventDto findById(int id) {
		Optional<Event> optional = eventRepository.findById(id);
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return null;
		Event event = optional.get();
		return entityToDto(event);
	}
	@Override
	public int countEvent() {
		// TODO Auto-generated method stub
		return eventRepository.countEvent();
	}
	
	
}
