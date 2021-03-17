package com.event.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

import com.event.common.ServiceInfo;
import com.event.dto.RoleDto;
import com.event.dto.UserDto;
import com.event.entity.Role;
import com.event.service.RoleService;
import com.event.service.UserService;

@Controller
@RequestMapping("admin/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		List<UserDto> users = userService.getAll();
		model.addAttribute("user",users);
		return "user/index";
	}
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		List<RoleDto> roles = roleService.getAll();
		model.addAttribute("role",roles);
		UserDto user = new UserDto();
		model.addAttribute("users",user);
		return "/user/add";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public String add(@ModelAttribute("users") UserDto dto,@RequestParam("file") MultipartFile file, Model model) {
		
		
		
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
		dto.setAvatar(name);
		//-------------
//		Path path = Paths.get("src/main/resources/static/uploads");
//		
//		if(file.isEmpty())
//			dto.setAvatar("");
//		try {
//			
//			InputStream inputStream = file.getInputStream();
////			Files.copy(inputStream, path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//			Files.copy(inputStream, path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//			(file.getOriginalFilename().toLowerCase());
//			System.out.println("avata 1: "+ dto.getAvatar());
//			System.out.println(dto.getId()+" : "+dto.getEmail()+" : "+dto.getFullname()+" : "+
//					dto.getPassword()+" : "+dto.getAvatar()+" : "+ dto.getPhone()+" : "+dto.getAddress()+" : "
//					+dto.getBirthday()+" : "+dto.isGender()+" : "+ dto.getRoleId()+" : "+dto.getRole());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		dto.setPassword("123");
		System.err.println(dto.getPassword());
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12));
		dto.setPassword(hashed);
		
		ServiceInfo info = userService.add(dto);
		
		
		if(info.isStatus()==false) {
			List<RoleDto> roles = roleService.getAll();
			model.addAttribute("role",roles);
			model.addAttribute("message", info.getMessage());
			return "user/add";
		}
		return "redirect:/admin/user";
	}
	@RequestMapping(value = "edit",method = RequestMethod.GET)
	public String edit(Model model, @RequestParam("id")int id) {
		UserDto dto = new UserDto();
		dto	= userService.findById(id);
		System.out.println(dto.getId()+" : "+dto.getEmail()+" : "+dto.getFullname()+" : "+
				dto.getPassword()+" : "+dto.getAvatar()+" : "+ dto.getPhone()+" : "+dto.getAddress()+" : "
				+dto.getBirthday()+" : "+dto.isGender()+" : "+ dto.getRoleId()+" : "+dto.getRole());
		model.addAttribute("users",dto);
		List<RoleDto> roles = roleService.getAll();
		model.addAttribute("role",roles);
		return "user/edit";
	}
	@RequestMapping(value = "edit",method = RequestMethod.POST)
	public String edit(@ModelAttribute("users") UserDto dto,@RequestParam("file") MultipartFile file, Model model, @RequestParam("id")int id) {
		
		String name=file.getOriginalFilename();
		System.err.println(dto.getAvatar());
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
            name=dto.getAvatar();
        }
		dto.setAvatar(name);
		
		ServiceInfo info = userService.update(dto);
		
		if(info.isStatus()==false)
		{
			model.addAttribute("message", info.getMessage());
			UserDto dto1 = new UserDto();
			dto1 = userService.findById(id);
			
			model.addAttribute("user",dto1);
			List<RoleDto> roles = roleService.getAll();
			model.addAttribute("role",roles);
			return "user/edit";
		}
		return "redirect:/admin/user";
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id")int id, Model model) {
		boolean delete = userService.delete(id);
		if(delete==true) {
			
			model.addAttribute("message","Xóa thành công");
			return "redirect:/admin/user";
		}
			
		else {
			model.addAttribute("message","Xóa thất bại");
			return "user";
		}
			
	}
	@RequestMapping(value = "changepassword", method = RequestMethod.GET)
	public String changePassword() {
		return "user/changepassword";
		
	}
//	@RequestMapping(value = "changepassword", method = RequestMethod.POST)
//	public String changePassword() {
//		return "user/changepassword";
//		
//	}
}
