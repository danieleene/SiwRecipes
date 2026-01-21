package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Ingrediente;
//import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;


	//Per ottenere la lista di tutti gli ingredienti
	public Iterable<Ingrediente> getAllIngredienti() {
		return ingredienteRepository.findAll();
	}
	
/*	//Per ottenere la lista delle ricette a partire dall' ID ingrediente
	public Iterable<Ricetta> findRicetteByIngredienteID(Long ingredienteID) {
		return this.ingredienteRepository.findRicetteByIngredienteID(ingredienteID);
	} */
	
	//Per ottenere l'ingrediente a partire dal suo ID
	public Ingrediente getIngredienteById(Long ingredienteID) {
        return this.ingredienteRepository.findById(ingredienteID).orElse(null);
    }
	
	//Per salvare un ingrediente
    public void saveIngrediente(Ingrediente newIngrediente) {
        this.ingredienteRepository.save(newIngrediente);
    }
    
    //Per salvare un nuovo ingrediente
    public Ingrediente saveNuovoIngrediente(Ingrediente ingrediente) {
        return this.ingredienteRepository.save(ingrediente);
    }
    
    //Per eliminare l'ingrediente a partire dal suo ID
    public void deleteIngredienteById(Long ingredienteID) {
        this.ingredienteRepository.deleteById(ingredienteID);
    }
    
    //Per cercare gli ingredienti
    public List<Ingrediente> searchIngredienti(String query) {
        return ingredienteRepository.findByNomeContainingIgnoreCase(query);
    }
	
}
