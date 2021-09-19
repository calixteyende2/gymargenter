package com.sygescom.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
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

import com.sygescom.entities.Adresse;
import com.sygescom.entities.Client;
import com.sygescom.entities.Notification;
import com.sygescom.entities.Plan;
import com.sygescom.entities.User;
import com.sygescom.entities.User_Role;
import com.sygescom.entities.Utilisateur;
import com.sygescom.repository.AdresseRepository;
import com.sygescom.repository.ClientRepository;
import com.sygescom.repository.NotificationRepository;
import com.sygescom.repository.PlanRepository;
import com.sygescom.repository.UserRepository;
import com.sygescom.repository.UserRoleRepository;
import com.sygescom.repository.UtilisateurRepository;
import com.sygescom.service.AsynchronousService;

@Controller
public class ClientController {
	 
	 
	 @Autowired
	 private ClientRepository clientRepository;
	 	 
	 @Autowired
	 private AdresseRepository adresseRepository;
	 
	 @Autowired
	 private PlanRepository planRepository;
	 
	 @Autowired
	 private NotificationRepository notificationRepository;
	 
	 @Autowired
	 private UtilisateurRepository utilisateurRepository;
	 
	 @Autowired
	 private UserRoleRepository userRoleRepository;
	 
	 
	 @Autowired
	 private UserRepository userRepository;
	   
	 @Autowired
	 private Notification notification;
	 

     @Autowired
     private AsynchronousService asynchronousService;	 
	 

	 	   
	 @Autowired
	 PasswordEncoder passwordEncoder;	
	 
		  
	
	@GetMapping(path = "/clients")
	public String listClients(
			Model model, 
			@RequestParam(name = "page", defaultValue = "0")int page, 
			@RequestParam(name = "size", defaultValue = "5")int size,
	        @RequestParam(name = "keyword", defaultValue = "")String mc){		
		Page<Client> pageClients = clientRepository.findAll(PageRequest.of(page, size));
		model.addAttribute("clients", pageClients.getContent());
		model.addAttribute("pages",new int[pageClients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword", mc);
		return "clients";
	}
	

	
	@GetMapping(path = "/deleteClient")
	public String deleteClient(Long id, String keyword, int page, int size, Model model) {
		utilisateurRepository.deleteById(id);
		return listClients(model, page, size, keyword);
	}

	

	@GetMapping(path="/clientForm")
	public String clientForm(Model model) {		
		List<Plan> services =  planRepository.findAll(); 
		System.out.println("registerForm opening");
		model.addAttribute("client", new Client());
		model.addAttribute("adresse", new Adresse());
		model.addAttribute("services", services);
		model.addAttribute("mode", "new");
		return "clientForm";
	}	
	
	
	
	// handler process for registering client
	@PostMapping(value="/saveClient")
	public String saveClient(
			Authentication authentication,
			@Valid @RequestBody @ModelAttribute(name="client") Client client, 
			@Valid @RequestBody @ModelAttribute("adresse") Adresse adresse, 
			BindingResult result,
			Model model) throws InterruptedException{
		System.out.println("clientForm opening");	
		if (result.hasErrors()) {
			System.out.println(result);
			return "clientForm";
		} 
		
									/* Unique Email Checking */
		if(client.getId() == null && (!utilisateurRepository .findAll().isEmpty())){ 	    	
	    	if(!utilisateurRepository.findAll().stream().noneMatch(e -> e.getEmail().equals(client.getEmail()))) {
				  
				  String message = "Adresse émail déjà presente: Veuillez entrez une autre adresse émail";
				  List<Plan> services =  planRepository.findAll();
				  model.addAttribute("client", client); 
				  model.addAttribute("adresse", adresse); 
				  model.addAttribute("services", services);
				  model.addAttribute("message", message);
				  model.addAttribute("mode", "new");
				  
				  return "clientForm"; 
				
	    		}	    	
		  }	
									/* Abonnement  Checking */		
				if((client.getCA()==null) && (client.getCGA()==null) && (client.getCGM()==null) && (client.getCGT()==null)
				&& (client.getCM()==null) && (client.getCT()==null)  && (client.getOPTCGA()==null) && (client.getOPTCGM()==null) 
				&& (client.getOPTCGT()==null) && (client.getQENTP()==0) && (client.getQPHYSIO()==0) && (client.getQNUTRI()==0)) {
				
					String message = "Veuillez choisir un plan d'abonnement svp!!!";
					List<Plan> services =  planRepository.findAll();
					model.addAttribute("client", client); 
					model.addAttribute("adresse", adresse); 
					model.addAttribute("services", services);
					model.addAttribute("message", message);
					model.addAttribute("mode", "new");
					return "clientForm";
		
				}
									/* Client  Saving */
				adresseRepository.save(adresse);
				adresse.getUtilisateurList().add(client);
				client.setPassword(passwordEncoder.encode(client.getPassword()));			
				clientRepository.save(client);
				//Ajout de notification au gestionnaire
				notification.setEmailGestionnaire(authentication.getName());
				notification.setEmailClient(client.getEmail());
				notification.setMessage("Fin abonnement dans 30 jours");
				notification.setDateFin(client.getDateFin());
				asynchronousService.processData(notification);
				//Ajout au registre authentification et Autorisation
				userRepository.save(new User(client.getEmail(), client.getPassword(), true));
				userRoleRepository.save(new User_Role(client.getEmail(), "CLIENT"));
				System.out.println("Client enregistré avec success");
				return "redirect:/clients";				 
	 				 
	  	}
		 

	
	@GetMapping(path="/editClient")
	public String editClient(Model model, Long id) {
		Utilisateur client = utilisateurRepository .findById(id).get();
		Adresse adresse = adresseRepository.findById(id).get();
		List<Plan> services = planRepository.findAll();  	
		model.addAttribute("client", client);
		model.addAttribute("adresse", adresse);
		model.addAttribute("services", services);
		model.addAttribute("mode", "edit");
		return "clientForm";
	}
	


	@GetMapping(path="/clientDetails")
	public String clientDetails(Model model, @RequestParam("email")String email) {
		Client client = clientRepository .findByEmail(email);
		Adresse adresse = adresseRepository.findById(client.getId()).get(); 		
		model.addAttribute("client", client);
		model.addAttribute("adresse", adresse);	
		model.addAttribute("mode", "edit");
		return "clientDetails";
	}
	

	
	@Async
	void serviceNotification() {
	  try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	} 
	  notificationRepository.save(notification);
	}
	
	
}

