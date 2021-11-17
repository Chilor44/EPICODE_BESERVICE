package it.epicode.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.model.Provincia;
import it.epicode.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRep;
	
	
	public Boolean existsBySigla(String sigla) {
		return provinciaRep.existsBySigla(sigla);
	}
	
	public String save(Provincia provincia) {
		provinciaRep.save(provincia);
		return "salvataggio avvenuto";
	}
	
	public String delete(Long id) {
		Provincia provincia =  (Provincia)provinciaRep.getById(id);
		provinciaRep.delete(provincia);
		return "provincia eliminata";
		
	}
	
	public String update(Long id, Provincia provincia) {
		Provincia provinciaUpdate = (Provincia) provinciaRep.getById(id);
		provinciaUpdate.setNome(provincia.getNome());
		provinciaUpdate.setSigla(provincia.getSigla());
		provinciaRep.save(provinciaUpdate);
		return "provincia aggiornata";
	}
	
	public Provincia getByNome(String nome) {
		
		return provinciaRep.getByNome(nome);
	}
	
}



