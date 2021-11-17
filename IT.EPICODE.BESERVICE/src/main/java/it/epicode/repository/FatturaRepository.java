package it.epicode.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.model.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long>{

	public Boolean existsByNumero(Long numero);
	
	@Query("SELECT f FROM Fattura f where f.cliente.id =:id")
	public Page<Fattura> getByClienteId(Pageable pageable, Long id);
	
	@Query("SELECT f FROM Fattura f where f.stato.nome =:stato")
	public Page<Fattura> getByStatoNome(Pageable pageable, String stato);
	
	public Page<Fattura> getByData(Pageable pageable, LocalDate date);
	
	public Page<Fattura> getByAnno(Pageable pageable, Integer anno);
	
	
	public Page<Fattura> getByImportoBetween(Pageable pageable, Double importo1, Double importo2);
}
