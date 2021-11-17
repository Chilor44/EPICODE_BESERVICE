package it.epicode.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.model.Comune;
@Component
public interface ComuneRepository extends JpaRepository<Comune, Long>{

	@Query("SELECT c from Comune c WHERE c.nome=:nome")
	public Comune getFirstByNome(String nome);
	public Boolean existsByNomeAndProvinciaSigla(String nome, String siglaProvincia);
	public Boolean existsByNome(String nome);
}
