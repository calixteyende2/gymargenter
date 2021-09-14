package com.gymargente.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gymargente.entities.Adresse;
import com.gymargente.entities.Plan;
import com.gymargente.entities.Specialiste;
import com.gymargente.entities.User;
import com.gymargente.entities.User_Role;
import com.gymargente.entities.Utilisateur;
import com.gymargente.repository.AdresseRepository;
import com.gymargente.repository.PlanRepository;
import com.gymargente.repository.SpecialisteRepository;
import com.gymargente.repository.UserRepository;
import com.gymargente.repository.UserRoleRepository;
import com.gymargente.repository.UtilisateurRepository;

@Controller
public class SpecialisteController {
	 
	 @Autowired
	 private UtilisateurRepository utilisateurRepository;
	 
	 @Autowired
	 private SpecialisteRepository specialisteRepository;
	 	 
	 @Autowired
	 private AdresseRepository adresseRepository;
	 
	 @Autowired
	 private PlanRepository planRepository;
	 
	 @Autowired
	 private UserRoleRepository userRoleRepository;
	 
	 
	 @Autowired
	 private UserRepository userRepository;
	 	 
	 	   
	 @Autowired
	 PasswordEncoder passwordEncoder;
	 
	 
	 private Plan service;
	 
	
	@GetMapping(path = "/specialistes")
	public String listSpecialistes(
			Model model, 
			@RequestParam(name = "page", defaultValue = "0")int page, 
			@RequestParam(name = "size", defaultValue = "5")int size,
	        @RequestParam(name = "keyword", defaultValue = "")String mc){		
		Page<Specialiste> pageSpecialistes = specialisteRepository.findAll(PageRequest.of(page, size));
		model.addAttribute("specialistes", pageSpecialistes.getContent());
		model.addAttribute("service", service);
		model.addAttribute("pages",new int[pageSpecialistes.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword", mc);
		return "specialistes";
	}
	

	
	@GetMapping(path = "/deleteSpecilaliste")
	public String deleteSpecialiste(Long id, String keyword, int page, int size, Model model) {
		utilisateurRepository.deleteById(id);
		return listSpecialistes(model, page, size, keyword);
	}
	
	

	@GetMapping(path="/specialisteForm")
	public String registerForm(Model model) {		
		List<Plan> services =  planRepository.findAll();
		System.out.println("registerForm opening");
		model.addAttribute("specialiste", new Specialiste());
		model.addAttribute("adresse", new Adresse());
		model.addAttribute("services", services);
		model.addAttribute("mode", "new");
		return "specialisteForm";
	}	
	
	
	
	// handler process for registering specialist
	@PostMapping(value="/saveSpecialiste")
	public String saveSpecialiste(
			Authentication authentication,
			@Valid @RequestBody @ModelAttribute(name="specialiste") Specialiste specialiste, 
			@Valid @RequestBody @ModelAttribute("adresse") Adresse adresse, 
			BindingResult result,
			Model model) throws InterruptedException{
		System.out.println("specialisteForm opening");	
		if (result.hasErrors()) {
			System.out.println(result);
			return "specialisteForm";
		} 
		
									/* Unique Email Checking */
		if(specialiste.getId() == null && (!utilisateurRepository .findAll().isEmpty())){ 	    	
	    	if(!utilisateurRepository.findAll().stream().noneMatch(e -> e.getEmail().equals(specialiste.getEmail()))) {
				  
				  String message = "Adresse émail déjà presente: Veuillez entrez une autre adresse émail"; 
				  List<Plan> services =  planRepository.findAll();
				  model.addAttribute("specialiste", specialiste); 
				  model.addAttribute("adresse", adresse); 
				  model.addAttribute("services", services);
				  model.addAttribute("message", message);
				  model.addAttribute("mode", "new");
				  
				  return "specialisteForm";
				  
	    		}	    	
		  }										 
						/* Service  Checking */
		 if((specialiste.getENTP()==null) && (specialiste.getNUTRI()==null) && (specialiste.getPHYSIO()==null)) {
			    
			  String message = "Veuillez choisir un service spécialisé si status = Spécialiste";
			  List<Plan> services =  planRepository.findAll();
			  model.addAttribute("specialiste", specialiste); 
			  model.addAttribute("adresse", adresse); 
			  model.addAttribute("services", services);
			  model.addAttribute("message", message);
			  model.addAttribute("mode", "new");
			  return "specialisteForm";				 
		 }											
		 				/* Specialist  Saving */
			  adresseRepository.save(adresse);
			  adresse.getUtilisateurList().add(specialiste);
			  specialiste.setPassword(passwordEncoder.encode(specialiste.getPassword()));			
			  specialisteRepository .save(specialiste);
			  userRepository.save(new User(specialiste.getEmail(), specialiste.getPassword(), true));
			  userRoleRepository.save(new User_Role(specialiste.getEmail(), "USER"));
			  System.out.println("Specialiste enregistré avec success");
			  System.out.println("specialiste  = "+ specialiste);
			  
			  return "redirect:/specialistes";				 
			
	  }
		 

	@GetMapping(path="/editSpecialiste")
	public String editSpecialiste(Model model, Long id) {
		Utilisateur specialiste = utilisateurRepository .findById(id).get();
		Adresse adresse = adresseRepository.findById(id).get();
		List<Plan> services = planRepository.findAll(); 	
		model.addAttribute("specialiste", specialiste);
		model.addAttribute("adresse", adresse);
		model.addAttribute("services", services);
		model.addAttribute("mode", "edit");
		return "specialisteForm";
	}
	
	
}

