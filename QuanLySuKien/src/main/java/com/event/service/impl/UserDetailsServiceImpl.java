package com.event.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.event.dto.UserDetailsDto;
import com.event.entity.Role;
import com.event.entity.User;
import com.event.repository.RoleRepository;
import com.event.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		//Gọi hàm findByEmail để kiểm tra mật khẩu
		User user = userRepository.findByEmail(email);
		
		//Trường hợp: sai email
		if(user == null) throw new UsernameNotFoundException("Sai email!");
		Role role = roleRepository.findById(user.getRoleId()).get();
		//Trường hợp: email đúng
		//Truy vấn bảng role để lấy thông tin tương ứng với user
			//Role role = user.getRole();
			//Tạo danh sách chứa tên quyền
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			//Tạo đối tượng có kiểu UserDetails 
			UserDetailsDto dto = new UserDetailsDto(user.getEmail(), user.getPassword(), authorities);
			
			//và Lưu thông tin user vào trong đó
		
		//Trả về đối tượng vừa tạo
	
	return dto;

	}
}
