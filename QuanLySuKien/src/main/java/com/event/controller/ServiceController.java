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

import com.event.common.ServiceInfo;
import com.event.dto.RoleDto;
import com.event.dto.ServiceDto;
import com.event.dto.UserDto;
import com.event.service.ServiceService;

@Controller
@RequestMapping("admin/service")
public class ServiceController {
	@Autowired
	ServiceService serviceService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		List<ServiceDto> services = serviceService.getAll();
		model.addAttribute("service",services);
		return "service/index";
	}
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		ServiceDto service = new ServiceDto();
		model.addAttribute("service",service);
		return "service/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("service")ServiceDto dto,@RequestParam("file") MultipartFile file) {
		
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
		
		ServiceInfo info = serviceService.add(dto);
		if(info.isStatus()==false)
		{
			model.addAttribute("message", info.getMessage());
			return "service/add";
		}
		
		return "redirect:/admin/service";
	}
	
	@RequestMapping(value = "edit",method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id")int id) {
		ServiceDto dto = new ServiceDto();
		dto	= serviceService.findById(id);
		
		model.addAttribute("service",dto);
		
		return "service/edit";
	}
	@RequestMapping(value = "edit",method = RequestMethod.POST)
	public String edit(@ModelAttribute("service") ServiceDto dto,@RequestParam("file") MultipartFile file, Model model, @RequestParam("id")int id) {
		String name=file.getOriginalFilename();
		System.err.println(dto.getBanner());
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
		ServiceInfo info = serviceService.update(dto);
		if(info.isStatus()==false)
		{
			model.addAttribute("message", info.getMessage());
			ServiceDto dto1 = new ServiceDto();
			dto1	= serviceService.findById(id);
			model.addAttribute("service",dto1);
			
			return "service/edit";
		}
		return "redirect:/admin/service";
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id")int id, Model model) {
		boolean delete = serviceService.delete(id);
		if(delete==true) {
			
			model.addAttribute("message","Xóa thành công");
			return "redirect:/admin/service";
		}
			
		else {
			model.addAttribute("message","Xóa thất bại");
			return "service";
		}
			
	}
}
