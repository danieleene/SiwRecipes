package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.RicettaRepository;
import org.springframework.stereotype.Service;

@Service
public class RicettaService {
  
  @Autowired
	private RicettaRepository ricettaRepository;

	//Per ottenere una ricetta a partire dall' ID
	public Ricetta getRicettaById(Long id) {
		return ricettaRepository.findById(id).get();
	}

	//Per ottenere la lista di tutte le ricette
	public Iterable<Ricetta> getAllRicette() {
		return ricettaRepository.findAll();
	}

	//Per salvare una ricetta
	public void saveRicetta(Ricetta nuovaRicetta) {
		this.ricettaRepository.save(nuovaRicetta);
	}

	//Per eliminare una ricetta a partire dal suo ID
	public void deleteRicettaById(Long ricettaID) {
		this.ricettaRepository.deleteById(ricettaID);
	}
	
	//Per cercare le ricette dalla barra di ricerca
	public List<Ricetta> searchRicette(String query){
		return this.ricettaRepository.searchRicette(query);
	}
  
}



