package com.sygescom.controller;


import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sygescom.entities.RendezVous;
import com.sygescom.entities.Client;
import com.sygescom.entities.Specialiste;
import com.sygescom.repository.RendezVousRepository;
import com.sygescom.repository.ClientRepository;
import com.sygescom.repository.SpecialisteRepository;

@Controller
public class RendezVousController {
	
	@Autowired
	private RendezVousRepository rendezVousRepository;
	
	@Autowired
	private SpecialisteRepository specialisteRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	private Specialiste specialiste;
	
	
		
	  @GetMapping(path = "/rendezVous") 
	  public String listCalandriers(
			  Model model, 
			  @RequestParam(name = "keyword", defaultValue = "")String mc,
			  @RequestParam(name = "page", defaultValue = "0")int page, 
			  @RequestParam(name = "size", defaultValue = "5")int size){
		  Page<RendezVous>pageRendezVous = rendezVousRepository.findAll(PageRequest.of(page, size));
		  model.addAttribute("rendezVous", pageRendezVous.getContent());
		  model.addAttribute("pages", new int[pageRendezVous.getTotalPages()]);
		  model.addAttribute("currentPage", page); 
		  return "rendezVous"; //List<RendezVous> rendezVous= rendezVousRepository.findAll(); 
	  }
	  
	  
	  	  
	  @GetMapping(path = "/userDeleteRendezVous") 
	  public String delete(
			  Long id, String keyword, int page, int size, Model model, 
			  @RequestParam(name = "email")String email) { 
		  rendezVousRepository.deleteById(id);
	      
		  return "redirect:/rendezVous?page="+page+"&size="+size+"&email="+email;
      }
	 
	  
	@GetMapping("/rendezVousForm")
	public String registerForm(
			Model model,
			  @RequestParam(name = "email")String email) {
		List<Client> clientsList = clientRepository.findAll();
		specialiste = specialisteRepository.findByEmail(email);
		model.addAttribute("clients", clientsList);
		model.addAttribute("specialiste", specialiste);
		model.addAttribute("rendezVous", new RendezVous());		
		model.addAttribute("mode", "new");
		System.out.println("rendezVousForm opening");
		System.out.println("Specialiste: " + specialiste);
		
		return "rendezVousForm";
	}
	
	
	
	@PostMapping("/userSaveRendezVous")
	public String saveCalendrier(
			Model model,
			@Valid @ModelAttribute("rendezVous") RendezVous rendezVous,
			@RequestParam(name = "client")Client client,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "5")int size,			  
			RedirectAttributes redirAttrs,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "rendezVousForm";
		}
		
		
		if(rendezVous.getDomaine().equals("ENTP")) {
			if(client.getQENTP()<rendezVous.getQuantite()) {
				List<Client> clientsList = clientRepository.findAll();
				Specialiste specialiste = specialisteRepository.findByEmail(rendezVous.getSpecialiste());
				model.addAttribute("participants", clientsList);
				model.addAttribute("specialiste", specialiste);
				model.addAttribute("message", "Pas assez d'heure du service entrainneur privé pour ce client!");
				model.addAttribute("rendezVous", new RendezVous());		
				model.addAttribute("mode", "new");	
				return "rendezVousForm";
			}else {
				client.setQENTP(client.getQENTP()-rendezVous.getQuantite());
				clientRepository.save(client);
				rendezVous.setClient(client.getEmail());
				rendezVousRepository.save(rendezVous);
				System.out.println(rendezVous);
				System.out.println("Rendez-vous enregistré avec success");
				return "redirect:/rendezVous?page="+page+"&size="+size+"&email="+rendezVous.getSpecialiste();
			}
		}
		if(rendezVous.getDomaine().equals("PHYSIO")) {
			if(client.getQPHYSIO()<rendezVous.getQuantite()) {
				List<Client> clientsList = clientRepository.findAll();
				Specialiste specialiste = specialisteRepository.findByEmail(rendezVous.getSpecialiste());
				model.addAttribute("participants", clientsList);
				model.addAttribute("specialiste", specialiste);
				model.addAttribute("message", "Pas assez d'heure du service physio pour ce client!");
				model.addAttribute("rendezVous", new RendezVous());		
				model.addAttribute("mode", "new");	
				return "rendezVousForm";
			}else {
				client.setQPHYSIO(client.getQPHYSIO()-rendezVous.getQuantite());
				clientRepository.save(client);
				rendezVous.setClient(client.getEmail());
				rendezVousRepository.save(rendezVous);
				System.out.println(rendezVous);
				System.out.println("Rendez-vous enregistré avec success");	
				return "redirect:/rendezVous?page="+page+"&size="+size+"&email="+rendezVous.getSpecialiste();
			}
		}
		if(rendezVous.getDomaine().equals("NUTRI")) {
			if(client.getQNUTRI()<rendezVous.getQuantite()) {
				List<Client> clientsList = clientRepository.findAll();
				Specialiste specialiste = specialisteRepository.findByEmail(rendezVous.getSpecialiste());
				model.addAttribute("participants", clientsList);
				model.addAttribute("specialiste", specialiste);
				model.addAttribute("message", "Pas assez d'heure du service nutritionniste pour ce client!");
				model.addAttribute("rendezVous", new RendezVous());		
				model.addAttribute("mode", "new");	
				return "rendezVousForm";
			}else {
				client.setQNUTRI(client.getQNUTRI()-rendezVous.getQuantite());
				clientRepository.save(client);
				rendezVous.setClient(client.getEmail());
				rendezVousRepository.save(rendezVous);
				System.out.println(rendezVous);
				System.out.println("Rendez-vous enregistré avec success");
				return "redirect:/rendezVous?page="+page+"&size="+size+"&email="+rendezVous.getSpecialiste();
			}
		}

		return "redirect:/rendezVous?page="+page+"&size="+size+"&email="+rendezVous.getSpecialiste();
	}
	

	@GetMapping("/userLireRendezVous")
	public String LireForm(Model model, Long id) {
		RendezVous rendezVous = rendezVousRepository.findById(id).get();
		model.addAttribute("rendezVous", rendezVous);
		model.addAttribute("mode", "edit");
		return "rendezVousDetails";
	}
	
	
	@GetMapping("/userEditRendezVous")
	public String editForm(Model model, Long id) {
		RendezVous rendezVous = rendezVousRepository.findById(id).get();
		model.addAttribute("rendezVous", rendezVous);
		model.addAttribute("mode", "edit");
		
		return "rendezVousForm";
	}
	

}


