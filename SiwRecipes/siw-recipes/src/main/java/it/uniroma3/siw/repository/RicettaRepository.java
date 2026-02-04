package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Ricetta;

public interface RicettaRepository extends CrudRepository<Ricetta, Long> {

  @Query("SELECT r FROM Ricetta r WHERE LOWER(r.titolo) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Ricetta> searchRicette(@Param("query") String query);

  List<Ricetta> findByCategoriaIgnoreCase(String categoria);

}


