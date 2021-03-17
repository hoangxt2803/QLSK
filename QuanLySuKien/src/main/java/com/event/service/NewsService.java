package com.event.service;

import java.util.List;

import com.event.dto.NewsDto;

public interface NewsService {
	List<NewsDto> getAll();
	boolean add(NewsDto dto);
	boolean delete(int id);
	boolean update(NewsDto dto);
	NewsDto findById(int id);
}
