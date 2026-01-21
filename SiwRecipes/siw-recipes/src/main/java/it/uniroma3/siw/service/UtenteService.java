package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;

@Service
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

	
}
