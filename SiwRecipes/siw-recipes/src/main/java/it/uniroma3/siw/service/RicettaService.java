package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.RicettaRepository;

public class RicettaService {
  
  @Autowired
	private RicettaRepository ricettaRepository;

	public Ricetta getRicettaById(Long id) {
		return ricettaRepository.findById(id).get();
	}

	public Iterable<Ricetta> getAllRicette() {
		return ricettaRepository.findAll();
	}
  
}

