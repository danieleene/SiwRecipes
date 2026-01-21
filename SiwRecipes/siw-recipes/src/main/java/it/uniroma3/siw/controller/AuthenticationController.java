package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredenzialiService;

@Controller
public class AuthenticationController {
	
	@Autowired CredenzialiService credenzialiService;
	
	//REGISTER FORM
	@GetMapping(value = "/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credenziali", new Credenziali());
		return "formRegisterUser.html";
	}
	
	
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute Credenziali credenziali, Model model) {
	    credenzialiService.saveCredenziali(credenziali);
	    return "redirect:/login";
	}

	
	//LOGIN FORM
	@GetMapping(value = "/login")
	public String showLoginForm(Model model) {
		return "formLogin";
	}
	
	//INDEX
	@GetMapping(value = "/")
	public String index(Model model) {
		return "index.html";
	}
	
	
}
