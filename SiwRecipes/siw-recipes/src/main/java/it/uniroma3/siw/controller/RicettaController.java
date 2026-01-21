package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.service.RicettaService;

@Controller
public class RicettaController {
  
  @Autowired RicettaService ricettaService;

	  //Risponde con una pagina che contiene la form per inserire i dati di un nuovo film
	  @GetMapping("/formNewRicetta")
	    public String formNewRicetta(Model model) {
	    model.addAttribute("ricetta", new Ricetta());
	    return "formNewRicetta.html";
	  }

	//Gestisce i dati di una nuova ricetta raccolti dalla form: se la ricetta non esiste,
	  //salva i dati nel db e risponde con una pagina che mostra i dati così come sono
	  //salvati nel db, se la ricetta esiste già risponde con la pagina che mostra la form 
	  //e con un messaggio di errore (la ricetta esiste)
	  @PostMapping("/ricetta")
	  public String newRicetta(@ModelAttribute("ricetta") Ricetta ricetta, Model model) {
		this.ricettaService.saveRicetta(ricetta);
	    model.addAttribute("ricetta", ricetta);
	      return "redirect:ricetta/" + ricetta.getId();
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


