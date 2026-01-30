package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.UtenteService;

@Controller
public class AutoreController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/autore/{id}")
    public String getAutore(@PathVariable("id") Long id, Model model) {

        Utente autore = utenteService.getUtenteById(id);

        if (autore == null) {
            return "notFound.html"; // opzionale DA IMPLEMENTARE!
        }

        model.addAttribute("autore", autore);
        model.addAttribute("ricette", autore.getRicette());

        return "autore.html";
    }
}
