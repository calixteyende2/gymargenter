package com.gymargente.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
	
	 @GetMapping(path = {"/", "/index"}) 
	 public String index() { 
		  return "index"; 		  
	 }
	 
		
	 @GetMapping(path = {"/abonnement"}) 
	 public String abonnement() { 
		  return "abonnement"; 		  
	 }
	 
	 @GetMapping(path = {"/contact"}) 
	 public String contact() { 
		  return "contact"; 		  
	 }
	 
	 
	 @GetMapping(path = "/template1") 
	 public String template1() { 
		  return "template1"; 		  
	 }
	 
	 @GetMapping(path = "/template2") 
	 public String template2() { 
	    return "template2"; 		  
	 }

}
