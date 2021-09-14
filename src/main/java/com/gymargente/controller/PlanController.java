package com.gymargente.controller;

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

import com.gymargente.entities.Plan;
import com.gymargente.repository.PlanRepository;

@Controller
public class PlanController {
	@Autowired
	private PlanRepository planRepository;
	 
	  
	@GetMapping(path = "/plans") 
	public String listPlans(
			  Model model, 
			  @RequestParam(name = "keyword", defaultValue = "")String mc,
			  @RequestParam(name = "page", defaultValue = "0")int page, 
			  @RequestParam(name = "size", defaultValue = "5")int size){ 
		  Page<Plan>pagePlans = planRepository.findByNameContains(mc, PageRequest.of(page, size));
		  model.addAttribute("plans", pagePlans.getContent());
		  model.addAttribute("pages", new int[pagePlans.getTotalPages()]);
		  model.addAttribute("currentPage", page); 
		  return "plans"; //List<Plan> plans= planRepository.findAll(); 
	 }
	 
	  
     @GetMapping(path = "/deletePlan") 
     public String delete(Long id, String keyword, int page, int size, Model model) { 
		  planRepository.deleteById(id); 
		  return listPlans(model, keyword, page, size);
     }
	 
	  
	@GetMapping("/planForm")
	public String serviceForm(Model model) {
		  System.out.println("planForm opening");
		  model.addAttribute("plan", new Plan());
		  model.addAttribute("mode", "new");
		  return "planForm";
	}	

	
	@PostMapping("/savePlan")
	public String saveService(@Valid @ModelAttribute("plan") Plan plan, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "planForm";
		}
		planRepository.save(plan);
		System.out.println(plan);
		System.out.println("Plan enregistré avec success");
		return "redirect:/plans";
	}
	
	
	@GetMapping("/editPlan")
	public String editForm(Model model, Long id) {
		Plan plan = planRepository.findById(id).get();
		model.addAttribute("plan", plan);
		model.addAttribute("mode", "edit");
		return "planForm";
	}

}















