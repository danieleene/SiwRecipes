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
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.UtenteService;

import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class UtenteController {
	
	@Autowired UtenteService utenteService;

	@Autowired CredenzialiService credenzialiService;
	
	@Autowired private PasswordEncoder passwordEncoder;

	
	  @GetMapping("/utente/{id}")
	  public String getUtente(@PathVariable("id") Long id, Model model) {

		  Utente utenteVisualizzato = this.utenteService.getUtenteById(id);
		  
		  //verifica delle credenziali
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  Credenziali cred = (Credenziali) auth.getPrincipal();
		  Utente utenteLoggato = cred.getUtente();
		  
		  model.addAttribute("utente", utenteVisualizzato); 
		  model.addAttribute("email", utenteVisualizzato.getCredenziali().getEmail()); 
		  model.addAttribute("utenteLoggato", utenteLoggato);
		  
	    return "profilo.html";
	  }

	  @GetMapping("/utente")
	  public String showUtenti(Model model) {
	    model.addAttribute("utenti", this.utenteService.getAllUtenti());
	    return "utenti.html";
	  }

	@GetMapping("/profilo")
	  public String mostraProfilo(Model model) {

		  //verifica credenziali
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      Credenziali cred = (Credenziali) auth.getPrincipal();
	      Utente utente = cred.getUtente();

	      model.addAttribute("utente", utente);
	      model.addAttribute("email", cred.getEmail());
		  model.addAttribute("utenteLoggato", utente);

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
		  model.addAttribute("credenziali", cred);
		  
	      return "formModificaProfilo.html";
	  }

	  
	  
	  @PostMapping("/profilo/modifica")
	  public String salvaModifiche(@ModelAttribute("utente") Utente utenteModificato,
								  @ModelAttribute("credenziali") Credenziali credModificate) {

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

		  // --- Aggiorna Credenziali ---
	      cred.setEmail(credModificate.getEmail());
	      if (credModificate.getPassword() != null && !credModificate.getPassword().isBlank()) {
	    	  cred.setPassword(passwordEncoder.encode(credModificate.getPassword()));
	      }

		  credenzialiService.saveCredenziali(cred);


	      return "redirect:/profilo";
	  }
	  

	  @GetMapping("/accessoNegato")
	  public String accessoNegato() {
	      return "accessoNegato.html";
	  }
	
}
