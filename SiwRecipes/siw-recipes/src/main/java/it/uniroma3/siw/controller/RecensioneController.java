package it.uniroma3.siw.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.RecensioneService;
import it.uniroma3.siw.service.RicettaService;

@Controller
public class RecensioneController {
	
	@Autowired RecensioneService recensioneService;

	@Autowired CredenzialiService credenzialiService;
	
	@Autowired RicettaService ricettaService;
	
	  @GetMapping("/recensione/{id}")
	  public String getRecensione(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("recensione", this.recensioneService.getRecensioneById(id));
	    return "recensione.html";
	  }

	  @GetMapping("/recensione")
	  public String showRecensioni(Model model) {
	    model.addAttribute("recensioni", this.recensioneService.getAllRecensioni());
	    return "recensioni.html";
	  }

	//Mostra il form per inserire una nuova recensione
	  @GetMapping("/ricetta/{id}/recensione/new")
	  public String formNewRecensione(@PathVariable("id") Long idRicetta,
	                                  Model model) {
		  
		// Utente NON loggato → redirect al login
		  Credenziali credenziali = credenzialiService.getCurrentCredentials();
	      if (credenziali == null)
	          return "redirect:/login";

	      Utente utente = credenziali.getUtente();

	      // BLOCCO UTENTE BANNATO
	      if (utente.getStato().equals("BANNATO"))
	          return "redirect:/accessDenied";

	      Ricetta ricetta = ricettaService.getRicettaById(idRicetta);
	      
	      model.addAttribute("ricetta", ricetta);
	      model.addAttribute("recensione", new Recensione());

	      return "formRecensione.html";
	  }
	  
	  //Metodo per salvare una recensione
	  @PostMapping("/ricetta/{id}/recensione")
	  public String addRecensione(@PathVariable("id") Long idRicetta,
	                              @ModelAttribute("recensione") Recensione recensione) {
		  
		  //FORZA LA RECENSIONE AD ESSERE NUOVA
		  recensione.setId(null);

	      // Utente NON loggato → redirect al login
	      Credenziali credenziali = credenzialiService.getCurrentCredentials();
	      if (credenziali == null)
	          return "redirect:/login";

	      Utente autore = credenziali.getUtente();

	      // BLOCCO UTENTE BANNATO
	      if (autore.getStato().equals("BANNATO"))
	          return "redirect:/accessDenied";

	      // Recupero ricetta
	      Ricetta ricetta = ricettaService.getRicettaById(idRicetta);

	      // Set campi obbligatori
	      recensione.setAutore(autore);
	      recensione.setRicetta(ricetta);
	      recensione.setData(new Date());

	      // Salvataggio
	      recensioneService.saveRecensione(recensione);

	      // Redirect alla pagina della ricetta
	      return "redirect:/ricetta/" + idRicetta;
	  }

	  @GetMapping("/recensione/{id}/delete")
	  public String deleteRecensione(@PathVariable("id") Long id) {

	      // Recupero recensione
	      Recensione recensione = recensioneService.getRecensioneById(id);
	      if (recensione == null)
	          return "redirect:/"; // fallback

	      // Recupero utente loggato
	      Credenziali credenziali = credenzialiService.getCurrentCredentials();
	      if (credenziali == null)
	          return "redirect:/login";

	      Utente utenteLoggato = credenziali.getUtente();

	      // Controllo permessi
	      boolean isOwner = recensione.getAutore().getId().equals(utenteLoggato.getId());
	      boolean isAdmin = credenziali.getRuolo().equals("ADMIN");

	      if (!isOwner && !isAdmin)
	          return "redirect:/accessDenied";

	      // Recupero ricetta per redirect finale
	      Ricetta ricetta = recensione.getRicetta();

	      // Rimozione coerente dalla relazione bidirezionale
	      ricetta.getRecensioni().remove(recensione);
	      recensione.setRicetta(null);

	      // Eliminazione
	      recensioneService.deleteRecensione(recensione);

	      // Redirect elegante alla ricetta
	      return "redirect:/ricetta/" + ricetta.getId();
	  }

	
}
