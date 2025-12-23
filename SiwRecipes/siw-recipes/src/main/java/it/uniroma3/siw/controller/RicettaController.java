package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.RicettaService;

@Controller
public class RicettaController {
  
  @Autowired RicettaService ricettaService;
	  
	  @GetMapping("/ricetta/{id}")
	  public String getRicetta(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("ricetta", this.ricettaService.getRicettaById(id));
	    return "ricetta.html";
	  }

	  @GetMapping("/ricetta")
	  public String showRicette(Model model) {
	    model.addAttribute("ricette", this.ricettaService.getAllRicette());
	    return "ricette.html";
	  }
  
}

