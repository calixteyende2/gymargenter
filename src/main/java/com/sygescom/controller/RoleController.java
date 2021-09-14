package com.sygescom.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sygescom.entities.Role;
import com.sygescom.repository.RoleRepository;

@Controller
public class RoleController {
	 

	 
	 @Autowired
	 private RoleRepository roleRepository;
	 
	  
	 @GetMapping(path = "/roles") 
	  public String listRoles(
			  Model model, 
			  @RequestParam(name = "keyword", defaultValue = "")String mc,
			  @RequestParam(name = "page", defaultValue = "0")int page, 
			  @RequestParam(name = "size", defaultValue = "5")int size){ 
		  Page<Role>pageRoles = roleRepository.findByRoleNameContains(mc, PageRequest.of(page, size));
		  model.addAttribute("roles", pageRoles.getContent());
		  model.addAttribute("pages", new int[pageRoles.getTotalPages()]);
		  model.addAttribute("currentPage", page); 
		  return "roles"; //List<Role> roles= faculteRepository.findAll(); 
	  }
	 
	  
	  @GetMapping(path = "/deleteRole") 
	  public String delete(Long id, String keyword, int page, int size, Model model) { 
		  roleRepository.deleteById(id); 
		  return listRoles(model, keyword, page, size);
	  }
	 
	  
	@GetMapping("/roleForm")
	public String roleForm(Model model) {
		System.out.println("roleForm opening");
		model.addAttribute("role", new Role());
		model.addAttribute("mode", "new");
		return "roleForm";
	}	

	
	@PostMapping("/saveRole")
	public String saveRole(@Valid @ModelAttribute("role") Role role, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "roleForm";
		}
		roleRepository.save(role);
		System.out.println(role);
		System.out.println("Role enregistré avec success");
		return "redirect:/roles";
	}
	
	@GetMapping("/editRole")
	public String editForm(Model model, Long id) {
		Role role = roleRepository.findById(id).get();
		model.addAttribute("role", role);
		model.addAttribute("mode", "edit");
		return "roleForm";
	}

}















