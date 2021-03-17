package com.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.entity.News;
@Repository
public interface NewsRepository  extends JpaRepository<News, Integer>{

}
