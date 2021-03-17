package com.event.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.event.common.InfoUsername;
import com.event.common.ServiceInfo;
//import com.event.dto.CustomerDto;
import com.event.dto.EventDto;
import com.event.dto.NewsDto;
import com.event.dto.ServiceDto;
import com.event.dto.UserDto;
import com.event.service.NewsService;
import com.event.service.UserService;

@Controller()
@RequestMapping("admin/news")
public class NewsController {

	@Autowired
	NewsService newsService;
	@Autowired
	UserService userService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		List<NewsDto> news = newsService.getAll();
		model.addAttribute("news", news);
		return "news/index";
	}
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		NewsDto news = new NewsDto();
		model.addAttribute("news",news);
		
		return "news/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("news") NewsDto dto,Model model,@RequestParam("file") MultipartFile file) {
		
		// banner
		String name=file.getOriginalFilename();
		if(!file.isEmpty()){
            try
            {
                byte[] bytes = file.getBytes();
                //Tạo đường dẫn lưu trữ file
                File dir = new File( "src/main/resources/static/upload");
                if(!dir.exists())
                    dir.mkdirs();
                
                File serverFile = new File(dir.getPath() + File.separator + name);
                BufferedOutputStream stream=new BufferedOutputStream( new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            }	
            catch (Exception e){
                return "File Error:" + e.getMessage();
            }
        }else {
            name=null;
        }
		dto.setBanner(name);
//		lấy id nhân viên
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		UserDto user = userService.findByEmail(username);
		dto.setUser_id(user.getId());
				
		
		
		boolean add = newsService.add(dto);
		if(add == true)
		{
			model.addAttribute("message", "Thêm thành công");
		}
		else {
			NewsDto news = new NewsDto();
			model.addAttribute("news",news);
			model.addAttribute("message", "Thêm thất bại");
			return "news/add";
		}
		
		return "redirect:/admin/news";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id")int id) {
		NewsDto news = newsService.findById(id);
		model.addAttribute("news",news);
		
		return "news/edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("news") NewsDto dto,@RequestParam("file") MultipartFile file,Model model, @RequestParam("id")int id) {
		String name=file.getOriginalFilename();
		if(!file.isEmpty()){
            try
            {
                byte[] bytes = file.getBytes();
                //Tạo đường dẫn lưu trữ file
                File dir = new File( "src/main/resources/static/upload");
                if(!dir.exists())
                    dir.mkdirs();
                
                File serverFile = new File(dir.getPath() + File.separator + name);
                BufferedOutputStream stream=new BufferedOutputStream( new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            }	
            catch (Exception e){
                return "File Error:" + e.getMessage();
            }
        }else {
            name=dto.getBanner();
        }
		dto.setBanner(name);
		
		boolean update = newsService.update(dto);
		if(update==false)
		{
			model.addAttribute("message", "Cập nhật thất bại");
			
			NewsDto news = newsService.findById(id);
			model.addAttribute("news",news);
			return "news/edit";
		}
		return "redirect:/admin/news";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id")int id, Model model) {
		boolean delete = newsService.delete(id);
		if(delete==true) {
			
			model.addAttribute("message","Xóa thành công");
			return "redirect:/admin/news";
		}
			
		else {
			model.addAttribute("message","Xóa thất bại");
			return "news";
		}
			
	}
}
