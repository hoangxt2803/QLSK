package com.event.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.event.dto.ServiceDto;
import com.event.dto.UserDetailsDto;
import com.event.dto.UserDto;
//import com.event.service.CustomerService;
import com.event.service.EventService;
import com.event.service.ServiceService;
import com.event.service.UserService;

@Controller
@RequestMapping("admin/event")
public class EventController {
	@Autowired
	EventService eventService;
	@Autowired
	ServiceService serviceService;
	@Autowired
	UserService userService;
//	@Autowired
//	CustomerService customerService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		List<EventDto> events = eventService.getAll();
		model.addAttribute("events",events);
////		lấy id nhân viên
//		InfoUsername infoUsername = new InfoUsername();
//		String username = infoUsername.getInfoUsername();
//		System.err.println(username);
		return "event/index";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
//		List<CustomerDto> customers = customerService.getAll();
//		model.addAttribute("customers",customers);
		List<ServiceDto> services = serviceService.getAll();
		model.addAttribute("services",services);
		EventDto event = new EventDto();
		model.addAttribute("event",event);
		InfoUsername infoUsername = new InfoUsername();
		String username = infoUsername.getInfoUsername();
		UserDto user = userService.findByEmail(username);
		event.setUserId(user.getId());
		
		return "event/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(Model model,@ModelAttribute("event") EventDto dto,@RequestParam("file") MultipartFile file) {
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
		System.err.println(username);
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
			return "event/add";
		}
		
		return "redirect:/admin/event";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id")int id) {
//		List<CustomerDto> customers = customerService.getAll();
//		model.addAttribute("customers",customers);
		List<ServiceDto> services = serviceService.getAll();
		model.addAttribute("services",services);
		EventDto event = eventService.findById(id);
		model.addAttribute("event",event);
		
		return "event/edit";
	}
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("event") EventDto dto,@RequestParam("file") MultipartFile file,Model model, @RequestParam("id")int id) {
		
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
		ServiceInfo info = eventService.update(dto);
		if(info.isStatus()==false)
		{
			model.addAttribute("message", info.getMessage());
//			List<CustomerDto> customers = customerService.getAll();
//			model.addAttribute("customers",customers);
			List<ServiceDto> services = serviceService.getAll();
			model.addAttribute("services",services);
			EventDto event = eventService.findById(id);
			model.addAttribute("event",event);
			return "event/edit";
		}
		return "redirect:/admin/event";
		
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id")int id, Model model) {
		boolean delete = eventService.delete(id);
		if(delete==true) {
			
			model.addAttribute("message","Xóa thành công");
			return "redirect:/admin/event";
		}
			
		else {
			model.addAttribute("message","Xóa thất bại");
			return "event";
		}
			
	}
}
