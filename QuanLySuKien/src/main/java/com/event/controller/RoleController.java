package com.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.common.ServiceInfo;
import com.event.dto.RoleDto;
import com.event.service.RoleService;

@Controller
@RequestMapping("admin/role")
public class RoleController {
	@Autowired
	RoleService roleService;
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String index(Model model) {  						// Model: truyền dữ liệu từ controller sang view
		List<RoleDto> dtos = roleService.getAll();				// dữ liệu từ form hoặc theo cả hai kiểu GET và POST.
		model.addAttribute("role", dtos);
		return "role/index";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		RoleDto dto = new RoleDto();
		model.addAttribute("role", dto);
		return "role/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("role") RoleDto dto, Model model ) { //@ModelAttribute: truyền dữ liệu từ view sang controller
		ServiceInfo info = roleService.add(dto);
		
		if(info.isStatus()==false)
		{
			model.addAttribute("message", info.getMessage());
			return "role/add";
		}
		
		return "redirect:/admin/role";								// điều hướng từ một trang sang trang khác  “redirect: url_trang_đích”
	}
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, Model model) {    //Annotation @RequestParam giúp lấy giá trị khi submit 
		RoleDto dto = roleService.findById(id);						// dữ liệu từ form hoặc theo cả hai kiểu GET và POST.
		model.addAttribute("role",dto);
		
		return "role/edit";
	}
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("role") RoleDto dto, Model model) {
		ServiceInfo info = roleService.update(dto.getId(), dto);
		if(info.isStatus()==false)
		{
			model.addAttribute("message", info.getMessage());
			return "role/edit";
		}
		return "redirect:/admin/role";
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") int id, Model model) {
		
		boolean delete = roleService.delete(id);
		if(delete==true) {
			model.addAttribute("message","Xóa thành công");
			return "redirect:/admin/role";
		}
			
		else {
			model.addAttribute("message","Xóa thất bại");
			return "role";
		}
	}
}
