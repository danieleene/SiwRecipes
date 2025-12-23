package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.service.IngredienteService;

@Controller
public class IngredienteController {
	
	  @Autowired IngredienteService ingredienteService;
	  

	  @GetMapping("/ingrediente")
	  public String showIngredienti(Model model) {
	    model.addAttribute("ingredienti", this.ingredienteService.getAllIngredienti());
	    return "ingredienti.html";
	  }

}
