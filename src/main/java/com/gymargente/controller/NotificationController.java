package com.gymargente.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gymargente.entities.Notification;
import com.gymargente.repository.NotificationRepository;

@Controller
public class NotificationController {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	
		
	  @GetMapping(path = "/notifications") 
	  public String listCalandriers(
			  Model model, 
			  @RequestParam(name = "keyword", defaultValue = "")String mc,
			  @RequestParam(name = "page", defaultValue = "0")int page, 
			  @RequestParam(name = "size", defaultValue = "5")int size){
		  Page<Notification>pageNotifoications = notificationRepository.findAll(PageRequest.of(page, size));
		  model.addAttribute("notifications", pageNotifoications.getContent());
		  model.addAttribute("pages", new int[pageNotifoications.getTotalPages()]);
		  model.addAttribute("currentPage", page); 
		  return "notifications"; //List<Notification> notifications= notificationRepository.findAll(); 
	  }
	  
  
	  @GetMapping(path = "/deleteNotification") 
	  public String delete(
			  Long id, String keyword, int page, int size, Model model) { 
	      notificationRepository.deleteById(id);	      
		  return "redirect:/notifications?page="+page+"&size="+size+"&keyword="+keyword;
      }
	 
	  
	  @GetMapping("/lireNotification")
	  public String LireForm(Model model, Long id) {
			Notification notification = notificationRepository.findById(id).get();
			model.addAttribute("notification", notification);
			model.addAttribute("mode", "edit");
			return "notificationDetails";
	  }
	

}


