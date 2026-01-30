package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
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

	@GetMapping("/profilo")
	  public String mostraProfilo(Model model) {

	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      Credenziali cred = (Credenziali) auth.getPrincipal();
	      Utente utente = cred.getUtente();

	      model.addAttribute("utente", utente);
	      model.addAttribute("email", cred.getEmail());

	      return "profilo.html";
	  }

	  
	  
	  
	  @GetMapping("/profilo/modifica")
	  public String modificaProfilo(Model model) {

		  
		  
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      Credenziali cred = (Credenziali) auth.getPrincipal();
	      Utente utente = cred.getUtente();
	      
	      if ("BANNATO".equals(utente.getStato())) {
	    	  return "redirect:/accessoNegato";
	      }

	      model.addAttribute("utente", utente);
	      return "formModificaProfilo.html";
	  }

	  
	  
	  @PostMapping("/profilo/modifica")
	  public String salvaModifiche(@ModelAttribute("utente") Utente utenteModificato) {

	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      Credenziali cred = (Credenziali) auth.getPrincipal();
	      Utente utente = cred.getUtente();

	      // Se è bannato → non può modificare nulla
	      if ("BANNATO".equals(utente.getStato())) {
	          return "redirect:/accessoNegato";
	      }

	      // Aggiorno solo i campi modificabili
	      utente.setNome(utenteModificato.getNome());
	      utente.setCognome(utenteModificato.getCognome());

	      utenteService.saveUtente(utente);

	      return "redirect:/profilo";
	  }
	  

	  @GetMapping("/accessoNegato")
	  public String accessoNegato() {
	      return "accessoNegato.html";
	  }
	
}
