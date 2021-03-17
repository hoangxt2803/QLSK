package com.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.event.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer>{
	public Service findByName(String name);
	@Query("SELECT count(s) from com.event.entity.Service s")
	public int countService();
}
