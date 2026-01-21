package it.uniroma3.siw.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Ingrediente;
//import it.uniroma3.siw.model.Ricetta;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {
	
	/* @Query("SELECT i.ricette FROM Ingrediente i WHERE i.id = :ingredienteID")
	    Iterable<Ricetta> findRicetteByIngredienteID(@Param("ingredienteID") Long ingredienteID);
*/
	 List<Ingrediente> findByNomeContainingIgnoreCase(String nome);
}
