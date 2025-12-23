package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.RecensioneService;

@Controller
public class RecensioneController {
	
	@Autowired RecensioneService recensioneService;
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

	
}
