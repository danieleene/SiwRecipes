package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.repository.RecensioneRepository;

@Service
public class RecensioneService {
	
	@Autowired
	private RecensioneRepository recensioneRepository;

	//per ottenere una recensione a partire dal suo id
	public Recensione getRecensioneById(Long id) {
		return recensioneRepository.findById(id).get();
	}

	//per ottenere l'elenco di tutte le recensioni
	public Iterable<Recensione> getAllRecensioni() {
		return recensioneRepository.findAll();
	}

	//per salvare una recensione(restituisce la recensione salvata)
	public Recensione saveRecensione(Recensione recensione) {
	    return this.recensioneRepository.save(recensione);
	}

	//per eliminare una recensione(non restituisce nulla)
	public void deleteRecensione(Recensione recensione) {
	    recensioneRepository.delete(recensione);
	}
	
}
