package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.event.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	@Query("SELECT count(u) from com.event.entity.Event u")
	public int countEvent();
	public List<Event> findByUserId(int id);
}
