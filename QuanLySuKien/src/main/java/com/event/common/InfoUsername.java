package com.event.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.event.dto.UserDetailsDto;
import com.event.dto.UserDto;
import com.event.service.UserService;

public class InfoUsername {
	
	
	public String getInfoUsername() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetailsDto) {
			 username = ((UserDetailsDto) principal).getUsername();
		}
		else {
			username = principal.toString();
		}
		
		return username;
	}
}
