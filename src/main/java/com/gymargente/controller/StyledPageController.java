package com.gymargente.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StyledPageController {
	
	@GetMapping("/styledPage1")
	public String styledPage1(Model model) {
		String name = "Calixte Yende";
		model.addAttribute("name", name);
		return "styledPage1";
	}
	
	@GetMapping("/styledPage2")
	public String styledPage2(Model model) {
		String name = "Calixte Yende";
		model.addAttribute("name", name);
		return "styledPage2";
	}

	@GetMapping("/styledPage3")
	public String styledPage3(Model model) {
		String name = "Calixte Yende";
		model.addAttribute("name", name);
		return "styledPage3";
	}
	
	@GetMapping("/styledPage4")
	public String styledPage4(Model model) {
		String name = "Calixte Yende";
		model.addAttribute("name", name);
		return "styledPage4";
	}
	
	@GetMapping("/styledPage5")
	public String styledPage5(Model model) {
		String name = "Calixte Yende";
		model.addAttribute("name", name);
		return "styledPage5";
	}
	
	@GetMapping("/styledPage7")
	public String getStyledPage7(Model model) {
	    model.addAttribute("name", "Baeldung Reader");
	    return "styledPage7";
	}
	
	
	
}















