package com.event.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.event.common.ServiceInfo;
import com.event.dto.ServiceDto;
import com.event.entity.Service;
import com.event.entity.User;
import com.event.repository.ServiceRepository;
import com.event.service.ServiceService;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService{
	@Autowired
	ServiceRepository serviceRepository;
	private ServiceDto entityToDto(Service entity) {
		return new ServiceDto(entity.getId(),entity.getName(),entity.getDescription(),entity.getContent(),entity.getBanner());
	}
	private Service dtoToEnity(ServiceDto dto) {
		return new Service(dto.getId(),dto.getName(),dto.getDescription(),dto.getContent(),dto.getBanner());
	}
	
	@Override
	public List<ServiceDto> getAll() {
		List<ServiceDto> dtos = new ArrayList<ServiceDto>();
		List<Service> entity = serviceRepository.findAll();
		for( Service service : entity) {
			dtos.add(entityToDto(service));
		}
		return dtos;
	}

	@Override
	public ServiceInfo add(ServiceDto dto) {
		Service entity = serviceRepository.findByName(dto.getName());
		if(entity != null)
			return new ServiceInfo(false, "Tên đã sử dụng!");
		else {
			try {
				Service service = new Service();
				service = dtoToEnity(dto);
				serviceRepository.save(service);
				return new ServiceInfo(true,"Thêm thành công!");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return new ServiceInfo(true,"Thêm thất bại!");
	}

	@Override
	public boolean delete(int id) {
		Optional<Service> optional = serviceRepository.findById(id);
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return false;
		Service service = optional.get();
		serviceRepository.delete(service);
		return true;
	}

	@Override
	public ServiceInfo update(ServiceDto dto) {
		//false không tìm thấy, true tìm thấy
		Optional<Service> optional = serviceRepository.findById(dto.getId());
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return new ServiceInfo(false, "Cập nhật thất bại!");
		Service service = optional.get();
//		Kiểm tra tên có trùng hay k
		Service entity = serviceRepository.findByName(dto.getName());
		if(entity!=null)
		{
			//Tên không thay đổi
			if(service.getName().equals(entity.getName())){
				service = dtoToEnity(dto);
				serviceRepository.save(service);
				return new ServiceInfo(true, "Cập nhật thành công!");
			}
			//trùng với tên khác đã có trong hệ thống
			else {
				return new ServiceInfo(false, "Tên đã được sử dụng!");
			}
		}
		else {
			try {
				service = dtoToEnity(dto);
				serviceRepository.save(service);
				return new ServiceInfo(true, "Cập nhật thành công!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ServiceInfo(true,"Cập nhật thất bại!");
	}

	@Override
	public ServiceDto findById(int id) {
		Optional<Service> optional = serviceRepository.findById(id);
		if(optional.isPresent()==false)
			return null;
		Service service = optional.get();
		return entityToDto(service);
	}
	@Override
	public int countService() {
		// TODO Auto-generated method stub
		return serviceRepository.countService();
	}
	
}
