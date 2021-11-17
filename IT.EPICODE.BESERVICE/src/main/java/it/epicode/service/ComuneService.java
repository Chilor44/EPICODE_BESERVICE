package it.epicode.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.model.Comune;
import it.epicode.model.Provincia;
import it.epicode.repository.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRep;
	@Autowired
	ProvinciaService provinciaService;
	
	
	public Boolean existsByNomeAndProvincia(String nome, Provincia prov) {
		return comuneRep.existsByNomeAndProvinciaSigla(nome, prov.getSigla());
	}
	
	public Boolean existsByNome(String nome) {
		return comuneRep.existsByNome(nome);
	}
	
	public Comune getComuneById(Long id) {
		return comuneRep.getById(id);
	}
	
	public Comune getByNome(String nome) {
		return comuneRep.getFirstByNome(nome);
	}
	
	public String save(Comune comune) {
		
		comuneRep.save(comune);
		return "salvataggio avvenuto";
	}
	
	public String deleteComune(Long id) {
		comuneRep.delete(comuneRep.getById(id));
		return "cliente eliminato";
		
	}
	
	public String updateComune(Long id, Comune comune) {
		Comune comuneUpdate = comuneRep.getById(id);
		comuneUpdate.setNome(comune.getNome());
		comuneUpdate.setProvincia(comune.getProvincia());
		comuneRep.save(comuneUpdate);
		return "comune aggiornato";
	}
}
