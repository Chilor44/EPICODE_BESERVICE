package it.epicode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.StatoFattura;
import it.epicode.service.StatoFatturaService;

@RestController
 @RequestMapping("/statoFatturaController")
public class StatoFatturaController {

	@Autowired
	StatoFatturaService statoFatturaService;
	
	
	@PostMapping("salvaStatoFattura")
	public String salvastatoFattura(@RequestBody StatoFattura stato) {
		return statoFatturaService.save(stato);
	}
	
	
	@PostMapping("eliminaStatoFattura/{id}")
	public String eliminaStatoFattura(@PathVariable (required = true) Long id) {
		return statoFatturaService.delete(id);
	}
	
	@PostMapping("updatestatoFattura")
	public String updateStatoFattura (@RequestBody StatoFattura stato) {
		return statoFatturaService.update(stato.getId(), stato);
	}
}
