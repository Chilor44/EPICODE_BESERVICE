package it.epicode.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.model.Provincia;
@Component
public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{

	public Provincia getByNome(String nome);
	public Boolean existsBySigla(String sigla);
}
