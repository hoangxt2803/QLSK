package com.event.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.event.dto.LienHeDto;
import com.event.dto.NewsDto;
//import com.event.entity.LienHe;
import com.event.entity.News;
import com.event.repository.NewsRepository;
import com.event.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsRepository newsRepository;
	private NewsDto entityToDto(News entity) {
		return new NewsDto(entity.getId(),entity.getTitle(),
				entity.getBanner(),entity.getContent(),
				entity.getUser_id());
	}
	private News dtoToEntity(NewsDto dto) {
		return new News(dto.getId(),dto.getTitle(),
				dto.getBanner(),dto.getContent(),
				dto.getUser_id());
	}
	@Override
	public List<NewsDto> getAll() {
		List<NewsDto> dtos = new ArrayList<NewsDto>();
		List<News> news = newsRepository.findAll();
		for(News n : news) {
			dtos.add(entityToDto(n));
		}
		return dtos;
	}

	@Override
	public boolean add(NewsDto dto) {
		try {
			News news = new News();
			news = dtoToEntity(dto);
			newsRepository.save(news);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		Optional<News> optional = newsRepository.findById(id);
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return false;
		News news = optional.get();
		newsRepository.delete(news);
		return true;
	}

	@Override
	public boolean update(NewsDto dto) {
		Optional<News> optional = newsRepository.findById(dto.getId());
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return false;
		try {
			News news = optional.get();
			news = dtoToEntity(dto);
			newsRepository.save(news);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public NewsDto findById(int id) {
		Optional<News> optional = newsRepository.findById(id);
		//Kiểm tra id tồn tại không?
		if(optional.isPresent()==false)
			return null;
		News news = optional.get();
		return entityToDto(news);
	}

}
