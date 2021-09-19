package com.sygescom.controller;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sygescom.entities.Adresse;
import com.sygescom.entities.Gestionnaire;
import com.sygescom.entities.Role;
import com.sygescom.entities.User;
import com.sygescom.entities.User_Role;
import com.sygescom.entities.Utilisateur;
import com.sygescom.repository.AdresseRepository;
import com.sygescom.repository.GestionnaireRepository;
import com.sygescom.repository.RoleRepository;
import com.sygescom.repository.UserRepository;
import com.sygescom.repository.UserRoleRepository;
import com.sygescom.repository.UtilisateurRepository;
import com.sygescom.wrapper.AdresseGestionnaireWrapper;

@Validated
@Controller
public class GestionnaireController {
	 
	 @Autowired
	 private UtilisateurRepository utilisateurRepository;
	 	 
	 
	 @Autowired
	 private GestionnaireRepository gestionnaireRepository;
	 
	 @Autowired
	 private AdresseRepository adresseRepository;
	 
	 	 
	 @Autowired
	 private UserRoleRepository userRoleRepository;
	 
	 @Autowired
	 private RoleRepository roleRepository;
	 
	 @Autowired
	 private UserRepository userRepository;
	 	 	   
	 @Autowired
	 PasswordEncoder passwordEncoder;	
	 
	 
		
	@GetMapping(path = "/gestionnaires")
	public String listGestionnaires(
			Model model, 
			@RequestParam(name = "page", defaultValue = "0")int page, 
			@RequestParam(name = "size", defaultValue = "5")int size,
	        @RequestParam(name = "keyword", defaultValue = "")String mc){		
		Page<Gestionnaire> pageGestionnaires = gestionnaireRepository.findAll(PageRequest.of(page, size));
		model.addAttribute("gestionnaires", pageGestionnaires.getContent());
		model.addAttribute("pages",new int[pageGestionnaires.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword", mc);
		return "gestionnaires";
	}
	  
  	  

	
	@GetMapping(path = "/deleteGestionnaire")
	public String deleteGestionnaire(Long id, String keyword, int page, int size, Model model) {
		utilisateurRepository.deleteById(id);
		return listGestionnaires(model, page, size, keyword);
	}
	  


	@GetMapping(path="/gestionnaireForm")
	public String gestionnaireForm(Model model) {	
		List<Role> roles = roleRepository.findAll();
		System.out.println("gestionnaireForm opening");
		model.addAttribute("adresseGestionnaire", new AdresseGestionnaireWrapper(new Gestionnaire(), new Adresse()));
		//model.addAttribute("adresse", new Adresse());
		model.addAttribute("roles", roles);
		model.addAttribute("mode", "new"); 
		return "gestionnaireForm";
	}	
	
	
	
	// handler process for registering gestionnaire
	@PostMapping(value="/saveGestionnaire")
	public String saveGestionnaire(
			Authentication authentication,
			@Valid @RequestBody @ModelAttribute(name="AdresseGestionnaire") AdresseGestionnaireWrapper adresseGestionnaire,
			BindingResult bindingResult,
			@Valid Model model) throws InterruptedException{
		System.out.println("gestionnaireForm opening");	
		if (bindingResult.hasErrors()) {		
			return "gestionnaireForm";
		} 
		
									/* Unique Email Checking */
		if(adresseGestionnaire.getGestionnaire().getId() == null && (!utilisateurRepository .findAll().isEmpty())){ 	    	
	    	if(!utilisateurRepository.findAll().stream().noneMatch(e -> e.getEmail().equals(adresseGestionnaire.getGestionnaire().getEmail()))) {
				  
				  String message = "Adresse émail déjà presente: Veuillez entrez une autre adresse émail";
				  model.addAttribute("adresseGestionnaire", adresseGestionnaire); 
				  //model.addAttribute("adresse", adresse);
				  model.addAttribute("message", message);
				  model.addAttribute("mode", "new");
				  
				  return "gestionnaireForm"; 
				  
	    		}	    	
		  }
   
		 							/* Gestionnaire  Saving */
		  adresseRepository.save(adresseGestionnaire.getAdresse());
		  adresseGestionnaire.getAdresse().getUtilisateurList().add(adresseGestionnaire.getGestionnaire());
		  adresseGestionnaire.getGestionnaire().setPassword(passwordEncoder.encode(adresseGestionnaire.getGestionnaire().getPassword()));			
		  gestionnaireRepository.save(adresseGestionnaire.getGestionnaire());
		  							//Ajout au registre authentification et Autorisation
		  userRepository.save(new User(adresseGestionnaire.getGestionnaire().getEmail(), adresseGestionnaire.getGestionnaire().getPassword(), true));
		  userRoleRepository.save(new User_Role(adresseGestionnaire.getGestionnaire().getEmail(), "ADMIN"));
		  System.out.println("utilisateur enregistré avec success");
		  System.out.println("Gestionnaire  = " + adresseGestionnaire);
		  
		  return "redirect:/gestionnaires";		
	  }
		 


	@GetMapping(path="/editGestionnaire")
	public String editGestionnaire(Model model, Long id) {
		Utilisateur gestionnaire = utilisateurRepository.findById(id).get();
		Adresse adresse = adresseRepository.findById(id).get(); 
		model.addAttribute("gestionnaire", gestionnaire);
		model.addAttribute("adresse", adresse);
		model.addAttribute("mode", "edit");
		return "gestionnaireForm";
	}
	
	
}

