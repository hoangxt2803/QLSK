package com.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	public Role findByName(String name);
}
