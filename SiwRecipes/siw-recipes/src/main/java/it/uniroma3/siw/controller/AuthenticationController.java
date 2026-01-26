package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.RicettaService;

@Controller
public class AuthenticationController {
	
	@Autowired CredenzialiService credenzialiService;
	
	@Autowired private PasswordEncoder passwordEncoder;

	@Autowired private RicettaService ricettaService;
	
	//REGISTER FORM
	@GetMapping(value = "/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credenziali", new Credenziali());
		return "formRegisterUser.html";
	}
	
	
	

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("credenziali") Credenziali credenziali) {

		// Il ruolo arriva dal form (USER o ADMIN)
		String ruolo = credenziali.getRuolo();
		credenziali.setRuolo(ruolo);

		//Codifica la password prima di salvarla
		credenziali.setPassword(passwordEncoder.encode(credenziali.getPassword()));


	    // Collego le entità (se non lo fa già il form)
	    Utente utente = credenziali.getUtente();
	    utente.setCredenziali(credenziali);

		//Imposto lo stato iniziale
	    utente.setStato("ATTIVO");

	    // Salvo SOLO credenziali (cascade salva anche utente)
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

		// Recupero autenticazione
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication != null && authentication.isAuthenticated()
	            && !(authentication instanceof AnonymousAuthenticationToken)) {

	        Credenziali cred = (Credenziali) authentication.getPrincipal();

	        model.addAttribute("email", cred.getEmail());
	        model.addAttribute("ruolo", cred.getRuolo());
	    }

		//Passo le ricette alla homepage (visibili a tutti)
	    model.addAttribute("ricette", ricettaService.getAllRicette());

	    return "index.html";
	}
	
	
}
