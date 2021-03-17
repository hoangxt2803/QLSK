package com.event.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.common.ServiceInfo;
import com.event.dto.RoleDto;
import com.event.entity.Role;
import com.event.repository.RoleRepository;
import com.event.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	private RoleDto entityToDto(Role role) {
		return new RoleDto(role.getId(),role.getName(),role.getDescription());
	}
	private Role dtoToEntity(RoleDto dto) {
		return new Role(dto.getId(),dto.getName(),dto.getDescription());
	}
	@Override
	public List<RoleDto> getAll() {
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		List<Role> roles = roleRepository.findAll();
		for(Role role: roles) {
			dtos.add(entityToDto(role));
		}
		return dtos;
	}

	@Override
	public ServiceInfo add(RoleDto dto) {
		Role entity = roleRepository.findByName(dto.getName());
		if(entity!=null) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		else {
			try {
				Role role = new Role();
				role = dtoToEntity(dto);
				roleRepository.save(role);
				return new ServiceInfo(true,"Thêm thành công!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ServiceInfo(true,"Thêm thất bại!");
	}

	@Override
	public boolean delete(int id) {
		Role role = roleRepository.findById(id).get();
		if(role != null)
		{
			roleRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public RoleDto findById(int id) {
		Role entity = roleRepository.findById(id).get();
		return entityToDto(entity);
	}
	@Override
	public ServiceInfo update(int id, RoleDto dto) {
		Optional<Role> optional = roleRepository.findById(id);
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return new ServiceInfo(false, "Cập nhật thất bại!");
		Role role = optional.get();
		
		Role entity = roleRepository.findByName(dto.getName());
		if(entity!=null) {
			if(role.getName()==entity.getName())
			{
				role = dtoToEntity(dto);
				roleRepository.save(role);
				return new ServiceInfo(true, "Cập nhật thành công!");
			}
			else
				return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		else {
			try {
				role = dtoToEntity(dto);
				roleRepository.save(role);
				return new ServiceInfo(true,"Cập nhật thành công!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ServiceInfo(true,"Thêm thất bại!");
	}
	@Override
	public RoleDto findByName(String name) {
		Role entity = roleRepository.findByName(name);
		return entityToDto(entity);
	}

}
