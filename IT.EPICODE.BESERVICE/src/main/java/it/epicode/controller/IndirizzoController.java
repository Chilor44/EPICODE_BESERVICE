package it.epicode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Indirizzo;
import it.epicode.service.ComuneService;
import it.epicode.service.IndirizzoService;

@RestController
@RequestMapping("/indirizzoController")
public class IndirizzoController {

	@Autowired
	IndirizzoService indirizzoService;
	@Autowired
	ComuneService comuneService;
	
	@PostMapping("salvaIndirizzo")
	public String salvaIndirizzo(@RequestBody Indirizzo indirizzo) {
		return indirizzoService.save(indirizzo);
	}
	
	@PostMapping("salvaIndirizzoParam")
	public String salvaIndirizzoParam (@RequestBody String via, String civico, String cap, String localita, String comune_nome) {
		return indirizzoService.save(new Indirizzo(via, civico, cap, localita, comuneService.getByNome(comune_nome)));
	}
	
	@PostMapping("eliminaIndirizzo/{id}")
	public String eliminaIndirizzo(@PathVariable (required = true) Long id) {
		return indirizzoService.delete(id);
	}
	
	
	@PostMapping("updateIndirizzo")
	public String updateIndirizzo(@RequestBody Indirizzo indirizzo) {
		return indirizzoService.update(indirizzo.getId(), indirizzo);
	}
}
