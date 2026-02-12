package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.UtenteService;

@Controller
public class AdminController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CredenzialiService credenzialiService;

    //Funzionalità ADMIN: per bannare un utente
    @PostMapping("/admin/utente/{id}/banna")
    public String bannaUtente(@PathVariable Long id) {
        Credenziali logged = credenzialiService.getCurrentCredentials();
    	
    	// Impedisce all'ADMIN di bannare sé stesso
    	if (logged.getUtente().getId().equals(id)) {
    		return "redirect:/error/operazione-non-permessa";
    	}
        
        Utente utente = utenteService.getUtenteById(id);
        utente.setStato("BANNATO");
        utenteService.saveUtente(utente);
        return "redirect:/admin/gestioneUtenti";
    }


    //Funzionalità ADMIN: per attivare un utente
    @PostMapping("/admin/utente/{id}/attiva")
    public String attivaUtente(@PathVariable Long id) {
        Utente utente = utenteService.getUtenteById(id);
        utente.setStato("ATTIVO");
        utenteService.saveUtente(utente);
        return "redirect:/admin/gestioneUtenti";
    }

    
    @GetMapping("/admin/gestioneUtenti")
    public String gestioneUtenti(Model model) {
        model.addAttribute("utenti", utenteService.getAllUtenti());
        return "admin/gestioneUtenti.html";
    }

}
