package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.repository.CredenzialiRepository;

@Service
public class CredenzialiService {
	
	@Autowired
	private CredenzialiRepository credenzialiRepository;

	public Credenziali getCredenzialiById(Long id) {
		return credenzialiRepository.findById(id).get();
	}

	public Iterable<Credenziali> getAllCredenziali() {
		return credenzialiRepository.findAll();
	}

	
}
