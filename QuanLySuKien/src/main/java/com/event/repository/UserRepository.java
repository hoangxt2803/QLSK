package com.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.event.dto.UserDto;
import com.event.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByEmail(String email);
	@Query("SELECT count(u) from com.event.entity.User u")
	public int countUser();
	
}
