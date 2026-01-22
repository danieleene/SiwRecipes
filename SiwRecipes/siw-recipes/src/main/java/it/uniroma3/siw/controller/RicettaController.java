package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.RicettaService;

@Controller
public class RicettaController {
  
  @Autowired RicettaService ricettaService;

@Autowired CredenzialiService credenzialiService;

	  //Risponde con una pagina che contiene la form per inserire i dati di una nuova ricetta
	  @GetMapping("/formNewRicetta")
	    public String formNewRicetta(Model model) {
			Credenziali cred = credenzialiService.getCurrentCredentials();
		    Utente utente = cred.getUtente();
		  
		   // BLOCCO UTENTE NON ATTIVO
		   if (!"ATTIVO".equals(utente.getStato())) {
			  return "accessoNegato.html";
		  }
	       model.addAttribute("ricetta", new Ricetta());
	       return "formNewRicetta.html";
	  }

	//Gestisce i dati di una nuova ricetta raccolti dalla form: se la ricetta non esiste,
	  //salva i dati nel db e risponde con una pagina che mostra i dati così come sono
	  //salvati nel db, se la ricetta esiste già risponde con la pagina che mostra la form 
	  //e con un messaggio di errore (la ricetta esiste)
	  @PostMapping("/ricetta")
	  public String newRicetta(@ModelAttribute("ricetta") Ricetta ricetta) {

		  Credenziali cred = credenzialiService.getCurrentCredentials();
		  Utente utente = cred.getUtente();
		  
		// BLOCCO UTENTE NON ATTIVO
		  if (!"ATTIVO".equals(utente.getStato())) {
			  return "accessoNegato.html";
		  }
		  
		  Ricetta salvata = this.ricettaService.saveRicetta(ricetta);
	      
	      return "redirect:ricetta/" + salvata.getId();
	  }

      //Risponde con una pagina che contiene i dettagli della ricetta	  
	  @GetMapping("/ricetta/{id}")
	  public String getRicetta(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("ricetta", this.ricettaService.getRicettaById(id));
	    return "ricetta.html";
	  }

	  //Risponde con una pagina che contiene la lista di tutte le ricette 
	  @GetMapping("/ricetta")
	  public String showRicette(Model model) {
	    model.addAttribute("ricette", this.ricettaService.getAllRicette());
	    return "ricette.html";
	  }
  
}



