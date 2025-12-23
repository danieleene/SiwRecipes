package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.UtenteService;

@Controller
public class UtenteController {
	
	@Autowired UtenteService utenteService;
	  @GetMapping("/utente/{id}")
	  public String getUtente(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("utente", this.utenteService.getUtenteById(id));
	    return "utente.html";
	  }

	  @GetMapping("/utente")
	  public String showUtenti(Model model) {
	    model.addAttribute("utenti", this.utenteService.getAllUtenti());
	    return "utenti.html";
	  }

	
}
