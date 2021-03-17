package com.event.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.common.ServiceInfo;
import com.event.dto.UserDto;
import com.event.entity.Role;
import com.event.entity.User;
import com.event.repository.RoleRepository;
import com.event.repository.UserRepository;
import com.event.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	private UserDto entityToDto(User entity) {
		return new UserDto(entity.getId(),entity.getEmail(),entity.getFullname(),
				entity.getPassword(), entity.getAvatar(), entity.getPhone(),entity.getAddress(),entity.getBirthday(),entity.isGender(), entity.getRoleId(),entity.getRole());
	}
	private User dtoToEntity(UserDto dto) {
		return new User(dto.getId(),dto.getEmail(),dto.getFullname(),
				dto.getPassword(), dto.getAvatar(), dto.getPhone(),dto.getAddress(),dto.getBirthday(),dto.isGender(), dto.getRoleId(),dto.getRole());
	}
	@Override
	public List<UserDto> getAll() {
		List<UserDto> dtos = new ArrayList<UserDto>();
		List<User> users = userRepository.findAll();
		for(User user:users)
		{
			dtos.add(entityToDto(user));
		}
		return dtos;
	}

	@Override
	public ServiceInfo add(UserDto dto) {
		User entity = userRepository.findByEmail(dto.getEmail());
		if(entity!=null) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		else {
			try {
				User user = new User();
				user = dtoToEntity(dto);
				userRepository.save(user);
				return new ServiceInfo(true,"Thêm thành công!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ServiceInfo(true,"Thêm thất bại!");
	}

	@Override
	public boolean delete(int id) {
		Optional<User> optional = userRepository.findById(id);
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return false;
		User user = optional.get();
		userRepository.delete(user);
		return true;
	}

	@Override
	public ServiceInfo update(UserDto dto) {
		Optional<User> optional = userRepository.findById(dto.getId());
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return new ServiceInfo(false, "Cập nhật thất bại!");
		User user = optional.get();
		Optional<Role> opt = roleRepository.findById(dto.getRoleId());
		if(opt.isPresent()==false)
			return new ServiceInfo(false, "Cập nhật thất bại role!");
		Role role = opt.get();
		User entity = userRepository.findByEmail(dto.getEmail());
		if(entity!=null) {
			if(user.getEmail().equals(entity.getEmail())) {
				user = dtoToEntity(dto);
				user.setRole(role);
				//userRepository.update1(user.getFullname(), user.getEmail(), user.getPhone(), user.getAddress(), user.getRoleId(), user.getAvatar(), id);
				userRepository.save(user);
				return new ServiceInfo(true, "Cập nhật thành công!");
			}
			else
				return new ServiceInfo(false, "Tên đã được sử dụng!");
			}
		else {
			try {
				user = dtoToEntity(dto);
				userRepository.save(user);
				//userRepository.save(user);
				return new ServiceInfo(true,"Cập nhật thành công!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ServiceInfo(true,"Cập nhật thất bại!");

	}

	@Override
	public UserDto findById(int id) {
		Optional<User> optional = userRepository.findById(id);
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return null;
		User user = optional.get();
		return entityToDto(user);
	}
	@Override
	public UserDto findByEmail(String email) {
		User optional = userRepository.findByEmail(email);
		
		return entityToDto(optional);
	}
	@Override
	public int countUser() {
		// TODO Auto-generated method stub
		return userRepository.countUser();
	}
	
}
