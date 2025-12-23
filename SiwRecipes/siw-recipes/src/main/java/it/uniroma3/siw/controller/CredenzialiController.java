package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.CredenzialiService;

@Controller
public class CredenzialiController {
	
	@Autowired CredenzialiService credenzialiService;
	  @GetMapping("/credenziali/{id}")
	  public String getCredenziali(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("credenziali", this.credenzialiService.getCredenzialiById(id));
	    return "credenziali.html";
	  }

	  @GetMapping("/credenziali")
	  public String showCredentials(Model model) {
	    model.addAttribute("credentials", this.credenzialiService.getAllCredenziali());
	    return "credentials.html";
	  }

	
}
