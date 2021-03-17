package com.event.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
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
import com.event.dto.EventDto;
import com.event.dto.NewsDto;
import com.event.dto.RoleDto;
import com.event.dto.ServiceDto;
import com.event.dto.UserDto;
import com.event.service.EventService;
import com.event.service.NewsService;
import com.event.service.ServiceService;
import com.event.service.UserService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	EventService eventService;
	@Autowired
	ServiceService serviceService;
	@Autowired
	NewsService newsService;
	@Autowired
	UserService userService;
	@RequestMapping(value = "", method =  RequestMethod.GET)
	public String home(Model model) {
		//danh sach loại su kien
		List<ServiceDto> loaiSK = serviceService.getAll();
		model.addAttribute("services",loaiSK);
//		List<ServiceDto> services =  new ArrayList<ServiceDto>();
//		for(int i=0;i<6;i++) {
//			services.add(loaiSK.get(i));
//		}
//		model.addAttribute("services",services);
//		//tin tuc
		List<NewsDto> tintuc = newsService.getAll();
//		System.err.println(tintuc);
		if(tintuc.isEmpty() == false) {
			List<NewsDto> news = new ArrayList<NewsDto>();
//			System.err.println(tintuc.size());
			if(tintuc.size()>6) {
				
				for(int i=0;i<6;i++) {
					news.add(tintuc.get(i));
				}
				
			}
			else {
				for(int i=0;i<tintuc.size();i++) {
					news.add(tintuc.get(i));
				}
				
			}
			model.addAttribute("news",news);
			
		}
		else {
			model.addAttribute("news",tintuc);
		}
		//su kien
		List<EventDto> sukien = eventService.getAll();
		
		if(sukien.isEmpty() == false) {
			List<EventDto> events = new ArrayList<EventDto>();
//			System.err.println("sk"+sukien.size());
			if(sukien.size()>6) {
				
				for(int i=0;i<6;i++) {
					events.add(sukien.get(i));
				}
				
			}
			else {
				for(int i=0;i<sukien.size();i++) {
					events.add(sukien.get(i));
//					System.err.println("tt"+sukien.size());
				}
				
			}
			model.addAttribute("events",events);
			
		}
		else {
			model.addAttribute("events",sukien);
		}
		
//		lấy id nhân viên
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		if(username =="anonymousUser")
		{
			model.addAttribute("username",username);
		}
		else {
			UserDto user = userService.findByEmail(username);
			model.addAttribute("username",username);
			model.addAttribute("user",user);
		}
//		System.err.println(username);

		return "home/home";
	}
	
	@RequestMapping(value = "news", method = RequestMethod.GET)
	public String news(Model model) {
//		lấy id nhân viên
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		if(username =="anonymousUser")
		{
			model.addAttribute("username",username);
		}
		else {
			UserDto user = userService.findByEmail(username);
			model.addAttribute("username",username);
			model.addAttribute("user",user);
		}
		List<NewsDto> tintuc = newsService.getAll();
		model.addAttribute("news",tintuc);
		return "home/news";
	}
	@RequestMapping(value = "news/detail", method = RequestMethod.GET)
	public String chitiet_tintuc(Model model,@RequestParam("id")int id) {
//		lấy id nhân viên
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		if(username =="anonymousUser")
		{
			model.addAttribute("username",username);
		}
		else {
			UserDto user = userService.findByEmail(username);
			model.addAttribute("username",username);
			model.addAttribute("user",user);
		}
		
		NewsDto news = newsService.findById(id);
		model.addAttribute("news",news);
		return "home/news_details";
	}
	@RequestMapping(value = "events", method = RequestMethod.GET)
	public String events(Model model) {
//		lấy id nhân viên
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		if(username =="anonymousUser")
		{
			model.addAttribute("username",username);
		}
		else {
			UserDto user = userService.findByEmail(username);
			model.addAttribute("username",username);
			model.addAttribute("user",user);
		}
		List<EventDto> events = eventService.getAll();
		model.addAttribute("events",events);
		return "home/event";
	}
	@RequestMapping(value = "event/detail", method = RequestMethod.GET)
	public String chitiet_sukien(Model model,@RequestParam("id")int id) {
//		lấy id nhân viên
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		if(username =="anonymousUser")
		{
			model.addAttribute("username",username);
		}
		else {
			UserDto user = userService.findByEmail(username);
			model.addAttribute("username",username);
			model.addAttribute("user",user);
		}
		
		EventDto events = eventService.findById(id);
		model.addAttribute("events",events);
		return "home/events_details";
	}
	@RequestMapping(value = "userDetail", method = RequestMethod.GET)
	public String thongTinCaNhan(Model model) {
//		lấy id nhân viên
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		if(username =="anonymousUser")
		{
			model.addAttribute("username",username);
		}
		else {
			UserDto user = userService.findByEmail(username);
			model.addAttribute("username",username);
			model.addAttribute("user",user);
			model.addAttribute("users",user);
		}
		return "home/thongTinCaNhan";
	}
	@RequestMapping(value = "userDetail", method = RequestMethod.POST)
	public String thongTinCaNhan(@ModelAttribute("users") UserDto dto,Model model) {
		//		lấy id nhân viên
		ServiceInfo info = userService.update(dto);
//		System.err.println(dto.getAvatar() + " " +dto.getRoleId());
		if(info.isStatus()==false)
		{
//			System.err.println("Lỗi");
			model.addAttribute("message", info.getMessage());
			UserDto dto1 = new UserDto();
			dto1 = userService.findById(dto.getId());
			
			model.addAttribute("users",dto1);
			return "home/home";
		}
		return "redirect:home";
	}
	
	@RequestMapping(value = "taoSuKien", method = RequestMethod.GET)
	public String taoSuKien(Model model) {
//		lấy id nhân viên
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		if(username =="anonymousUser")
		{
			model.addAttribute("username",username);
			return "/home/login";
		}
		else {
			UserDto user = userService.findByEmail(username);
			model.addAttribute("username",username);
			model.addAttribute("user",user);
			model.addAttribute("users",user);
		}
		List<ServiceDto> services = serviceService.getAll();
		model.addAttribute("services",services);
		EventDto event = new EventDto();
		model.addAttribute("event",event);
		return "/home/taoSuKien";
	}
	
	@RequestMapping(value = "taoSuKien", method = RequestMethod.POST)
	public String taoSuKien(Model model,@ModelAttribute("event") EventDto dto,@RequestParam("file") MultipartFile file) {
//		// banner
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
//		System.err.println(username);
		UserDto user = userService.findByEmail(username);
		dto.setUserId(user.getId());
		
		//------------
		ServiceInfo info = eventService.add(dto);
		if(info.isStatus()==false)
		{
			model.addAttribute("message", info.getMessage());
//			List<CustomerDto> customers = customerService.getAll();
//			model.addAttribute("customers",customers);
			List<ServiceDto> services = serviceService.getAll();
			model.addAttribute("services",services);
			return "home/home";
		}
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "suKienCuaToi", method = RequestMethod.GET)
	public String suKienCuaToi(Model model) {
//		lấy id nhân viên
		
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		if(username =="anonymousUser")
		{
			model.addAttribute("username",username);
		}
		else {
			UserDto user = userService.findByEmail(username);
			model.addAttribute("username",username);
			model.addAttribute("user",user);
			List<EventDto> events = eventService.getAllByUserId(user.getId());
			model.addAttribute("events",events);
		}
		
		
		return "home/suKienCuaToi";
	}
	@RequestMapping(value = "doiMatKhau", method = RequestMethod.GET)
	public String doiMatKhau(Model model) {
//		lấy id nhân viên
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		if(username =="anonymousUser")
		{
			model.addAttribute("username",username);
		}
		else {
			UserDto user = userService.findByEmail(username);
			model.addAttribute("username",username);
			model.addAttribute("user",user);
		}
		return "home/doiMatKhau";
	}

	@RequestMapping(value = "dangKy", method = RequestMethod.GET)
	public String dangKy(Model model) {
		
		UserDto user = new UserDto();
		model.addAttribute("users",user);
		return "/home/dangKy";
	}
	@RequestMapping(value = "dangKy", method = RequestMethod.POST)
	public String dangKy(@ModelAttribute("users") UserDto dto, Model model, @RequestParam("confirmPassword")String confirmPassword) {
		
		UserDto user = new UserDto();
		model.addAttribute("users",user);
		System.err.println(dto.getPassword()+ " "+ confirmPassword);
		if(dto.getPassword().equals(confirmPassword))
		{	System.err.println("DANG ky");
			String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12));
			dto.setPassword(hashed);
			System.err.println("mk: "+hashed);
			System.err.println(dto.getPassword());
			dto.setPhone(null);
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			dto.setBirthday(date.format(formatter));
			dto.setAddress(null);
			dto.setGender(false);
			dto.setRoleId(2);
			
			ServiceInfo info = userService.add(dto);
			
			
			if(info.isStatus()==false) {
				model.addAttribute("message", info.getMessage());
				return "home/dangKy";
			}
			return "redirect:/home/login";
		}
		else {
			model.addAttribute("message", "Mật khẩu không khớp");
			return "home/dangKy";
		}
		
	}
}
