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

import com.sygescom.entities.Calandrier;
import com.sygescom.entities.Client;
import com.sygescom.entities.Specialiste;
import com.sygescom.repository.CalandrierRepository;
import com.sygescom.repository.ClientRepository;
import com.sygescom.repository.SpecialisteRepository;

@Controller
public class CalandrierController {
	
	@Autowired
	private CalandrierRepository calandrierRepository;
	
	@Autowired
	private SpecialisteRepository specialisteRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	private Specialiste specialiste;
	
	
		
	  @GetMapping(path = "/calandriers") 
	  public String listCalandriers(
			  Model model, 
			  @RequestParam(name = "keyword", defaultValue = "")String mc,
			  @RequestParam(name = "page", defaultValue = "0")int page, 
			  @RequestParam(name = "size", defaultValue = "5")int size){
		  Page<Calandrier>pageCalandriers = calandrierRepository.findAll(PageRequest.of(page, size));
		  model.addAttribute("calandriers", pageCalandriers.getContent());
		  model.addAttribute("pages", new int[pageCalandriers.getTotalPages()]);
		  model.addAttribute("currentPage", page); 
		  return "calandriers"; //List<Calendrier> calendriers= calendrierRepository.findAll(); 
	  }
	  
	  
	  	  
	  @GetMapping(path = "/userDeleteCalandrier") 
	  public String delete(
			  Long id, String keyword, int page, int size, Model model, 
			  @RequestParam(name = "email")String email) { 
	      calandrierRepository.deleteById(id);
	      
		  return "redirect:/calandriers?page="+page+"&size="+size+"&email="+email;
      }
	 
	  
	@GetMapping("/calandrierForm")
	public String registerForm(
			Model model,
			  @RequestParam(name = "email")String email) {
		List<Client> clientsList = clientRepository.findAll();
		specialiste = specialisteRepository.findByEmail(email);
		model.addAttribute("clients", clientsList);
		model.addAttribute("specialiste", specialiste);
		model.addAttribute("calandrier", new Calandrier());		
		model.addAttribute("mode", "new");
		System.out.println("calandrierForm opening");
		System.out.println("Specialiste: " + specialiste);
		
		return "calandrierForm";
	}
	
	
	
	@PostMapping("/userSaveCalandrier")
	public String saveCalendrier(
			Model model,
			@Valid @ModelAttribute("calandrier") Calandrier calandrier,
			@RequestParam(name = "client")Client client,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "5")int size,			  
			RedirectAttributes redirAttrs,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "calandrierForm";
		}
		
		
		if(calandrier.getDomaine().equals("ENTP")) {
			if(client.getQENTP()<calandrier.getQuantite()) {
				List<Client> clientsList = clientRepository.findAll();
				Specialiste specialiste = specialisteRepository.findByEmail(calandrier.getSpecialiste());
				model.addAttribute("participants", clientsList);
				model.addAttribute("specialiste", specialiste);
				model.addAttribute("message", "Pas assez d'heure du service entrainneur privé pour ce client!");
				model.addAttribute("calandrier", new Calandrier());		
				model.addAttribute("mode", "new");	
				return "calandrierForm";
			}else {
				client.setQENTP(client.getQENTP()-calandrier.getQuantite());
				clientRepository.save(client);
				calandrier.setClient(client.getEmail());
				calandrierRepository.save(calandrier);
				System.out.println(calandrier);
				System.out.println("Calandrier enregistré avec success");
				return "redirect:/calandriers?page="+page+"&size="+size+"&email="+calandrier.getSpecialiste();
			}
		}
		if(calandrier.getDomaine().equals("PHYSIO")) {
			if(client.getQPHYSIO()<calandrier.getQuantite()) {
				List<Client> clientsList = clientRepository.findAll();
				Specialiste specialiste = specialisteRepository.findByEmail(calandrier.getSpecialiste());
				model.addAttribute("participants", clientsList);
				model.addAttribute("specialiste", specialiste);
				model.addAttribute("message", "Pas assez d'heure du service physio pour ce client!");
				model.addAttribute("calandrier", new Calandrier());		
				model.addAttribute("mode", "new");	
				return "calandrierForm";
			}else {
				client.setQPHYSIO(client.getQPHYSIO()-calandrier.getQuantite());
				clientRepository.save(client);
				calandrier.setClient(client.getEmail());
				calandrierRepository.save(calandrier);
				System.out.println(calandrier);
				System.out.println("Calandrier enregistré avec success");	
				return "redirect:/calandriers?page="+page+"&size="+size+"&email="+calandrier.getSpecialiste();
			}
		}
		if(calandrier.getDomaine().equals("NUTRI")) {
			if(client.getQNUTRI()<calandrier.getQuantite()) {
				List<Client> clientsList = clientRepository.findAll();
				Specialiste specialiste = specialisteRepository.findByEmail(calandrier.getSpecialiste());
				model.addAttribute("participants", clientsList);
				model.addAttribute("specialiste", specialiste);
				model.addAttribute("message", "Pas assez d'heure du service nutritionniste pour ce client!");
				model.addAttribute("calandrier", new Calandrier());		
				model.addAttribute("mode", "new");	
				return "calandrierForm";
			}else {
				client.setQNUTRI(client.getQNUTRI()-calandrier.getQuantite());
				clientRepository.save(client);
				calandrier.setClient(client.getEmail());
				calandrierRepository.save(calandrier);
				System.out.println(calandrier);
				System.out.println("Calandrier enregistré avec success");
				return "redirect:/calandriers?page="+page+"&size="+size+"&email="+calandrier.getSpecialiste();
			}
		}

		return "redirect:/calandriers?page="+page+"&size="+size+"&email="+calandrier.getSpecialiste();
	}
	

	@GetMapping("/userLireCalandrier")
	public String LireForm(Model model, Long id) {
		Calandrier calandrier = calandrierRepository.findById(id).get();
		model.addAttribute("calandrier", calandrier);
		model.addAttribute("mode", "edit");
		return "calandrierDetails";
	}
	
	
	@GetMapping("/userEditCalandrier")
	public String editForm(Model model, Long id) {
		Calandrier calandrier = calandrierRepository.findById(id).get();
		model.addAttribute("calandrier", calandrier);
		model.addAttribute("mode", "edit");
		
		return "calandrierForm";
	}
	

}


