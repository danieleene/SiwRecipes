package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.repository.CredenzialiRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class CredenzialiService {
	
	@Autowired
	private CredenzialiRepository credenzialiRepository;

	//Per ottenere le credenziali partendo dall'ID
	public Credenziali getCredenzialiById(Long id) {
		return credenzialiRepository.findById(id).get();
	}

	//Per ottenere tutte le credenziali
	public Iterable<Credenziali> getAllCredenziali() {
		return credenzialiRepository.findAll();
	}

	//Per ottenere le credenziali da username(NO) email
	public Credenziali getCredenziali(String email) {
        Optional<Credenziali> result = this.credenzialiRepository.findByEmail(email);
        return result.orElse(null);
    }

	//Per salvare le credenziali
	public Credenziali saveCredenziali(Credenziali credenziali) {
        //credentials.setRole("DEFAULT"); // Set default role
        //credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credenzialiRepository.save(credenziali);
    }

	//Per ottenere le credenziali dell'utente attualmente autenticato
	public Credenziali getCurrentCredentials() {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    String email;

	    if (principal instanceof UserDetails) {
	        email = ((UserDetails) principal).getUsername();
	    } else {
	        email = principal.toString();
	    }

	    return this.getCredenziali(email);
	}
}
