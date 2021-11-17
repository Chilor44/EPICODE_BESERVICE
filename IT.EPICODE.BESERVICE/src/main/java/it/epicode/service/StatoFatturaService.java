package it.epicode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.epicode.model.StatoFattura;
import it.epicode.repository.StatoFatturaRepository;

@Service
public class StatoFatturaService {

	@Autowired
	StatoFatturaRepository statoFatturaRep;
	
	public String save(StatoFattura stato) {
		statoFatturaRep.save(stato);
		return "salvataggio stato fattura avvenuto";
	}
	
	public StatoFattura getByNome(String nome) {
		return statoFatturaRep.getFirstByNome(nome);
	}
	
	
	public String delete(Long id) {
		StatoFattura statoF = statoFatturaRep.getById(id);
		statoFatturaRep.delete(statoF);
		return "stato fattura eliminato";
		
	}
	
	public String update(Long id, StatoFattura stato) {
		StatoFattura statoUpdate =statoFatturaRep.getById(id);
		statoUpdate.setNome(stato.getNome());
		statoFatturaRep.save(statoUpdate);
		return "stato fattura aggiornato";
	}
	
	public Boolean existsByNome(String nome) {
		return statoFatturaRep.existsByNome(nome);
	}
	
}
