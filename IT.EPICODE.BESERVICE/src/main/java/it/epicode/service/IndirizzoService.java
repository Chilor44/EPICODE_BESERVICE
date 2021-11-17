package it.epicode.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.model.Indirizzo;
import it.epicode.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRep;
	@Autowired
	ComuneService comuneService;
	
	
	public Boolean existsByVia(String via) {
		return indirizzoRep.existsByVia(via);
	}
	
		
		public String save(Indirizzo indirizzo) {
			
			indirizzoRep.save(indirizzo);
			return "salvataggio avvenuto";
		}
		
		
		public String delete(Long id) {
			Indirizzo indirizzo = indirizzoRep.getById(id);
			indirizzoRep.delete(indirizzo);
			return "indirizzo eliminato";
			
		}
		
		public String update(Long id, Indirizzo indirizzo) {
			Indirizzo indirizzoUpdate = indirizzoRep.getById(id);
			indirizzoUpdate.setVia(indirizzo.getVia());
			indirizzoUpdate.setCivico(indirizzo.getCivico());
			indirizzoUpdate.setCap(indirizzo.getCap());
			indirizzoUpdate.setLocalita(indirizzo.getLocalita());
			indirizzoUpdate.setComune(indirizzo.getComune());
			indirizzoRep.save(indirizzoUpdate);
			return "indirizzo aggiornato";
		}
		
		
		 public Indirizzo getById(Long id) {
		      return indirizzoRep.getById(id);
		     
		    }
		 
		 public Indirizzo getByVia(String via) {
			 return indirizzoRep.getByVia(via);
		 }
		
		 public void controlloIndirizzo(String via1, String civico1, String cap1, String localita1, String nomeComune1) {
			 if(!indirizzoRep.existsByVia(via1)) {
					Indirizzo indirizzo1 = new Indirizzo(via1, civico1, cap1, localita1, comuneService.getByNome(nomeComune1) );
					indirizzoRep.save(indirizzo1);
				}
		 }
		
}
