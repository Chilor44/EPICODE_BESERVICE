package it.epicode.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.model.Cliente;

@Component
public interface ClienteRepository extends JpaRepository<Cliente, Long>  {

	public Boolean existsByRagioneSociale(String ragionesociale);
	
	public Cliente getFirstByRagioneSociale(String nome);
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Page<Cliente> findAllByOrderByIndirizzoSedeLegaleComuneProvincia(Pageable pageable);
	
	public Page<Cliente> getByFatturatoAnnuale(Pageable pageable, Double fatturato);
	
	public Page<Cliente> getByDataInserimento(Pageable pageable, LocalDate data);
	
	public Page<Cliente> getByDataUltimoContatto(Pageable pageable, LocalDate data);
	
	public Page<Cliente> getByRagioneSocialeContainsIgnoreCase(Pageable pageable, String ragioneSociale);
}
//Iterable<Article> findAllByTitleContainsIgnoreCaseOrContentContains(String title, String content);
