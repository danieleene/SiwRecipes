package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;

	//Per ottenere l'utente a partire dal suo ID
	public Utente getUtenteById(Long id) {
		return utenteRepository.findById(id).get();
	}

	//Per ottenere la lista di tutti gli utenti
	public Iterable<Utente> getAllUtenti() {
		return utenteRepository.findAll();
	}

	//Per salvare un utente
	public Utente saveUtente(Utente utente) {
		return this.utenteRepository.save(utente);
	}

	//Per aggiungere una ricetta tra i preferiti
	public void aggiungiPreferito(Long idUtente, Ricetta ricetta) {
		
		Utente utente = this.utenteRepository.findById(idUtente).orElse(null);
		if (utente == null) return;
		
	    if (!utente.getRicettePreferite().contains(ricetta)) {
	        utente.getRicettePreferite().add(ricetta);
	        this.utenteRepository.save(utente);
	    }
	}

	//Per togliere una ricetta dai preferiti
	public void rimuoviPreferito(Long idUtente, Ricetta ricetta) {
		
		Utente utente = this.utenteRepository.findById(idUtente).orElse(null);
		if (utente == null) return;
		
	    if (utente.getRicettePreferite().contains(ricetta)) {
	        utente.getRicettePreferite().remove(ricetta);
	        this.utenteRepository.save(utente);
	    }	
}



}
