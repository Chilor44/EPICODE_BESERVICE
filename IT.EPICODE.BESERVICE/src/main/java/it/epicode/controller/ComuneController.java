package it.epicode.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Comune;
import it.epicode.service.ComuneService;
import it.epicode.service.ProvinciaService;

@RestController
@RequestMapping("/comuneController")
public class ComuneController {

	@Autowired
	ComuneService comuneService;
	@Autowired
	ProvinciaService provinciaService;

	
	@PostMapping("/salvaComune")
	public String salvaComune(@RequestBody Comune comune) {
		return comuneService.save(comune);
		
	}

	@GetMapping("/eliminacomune/{id}")
	public String eliminaComune(@PathVariable(required = true) Long id) {
		comuneService.deleteComune(id);
		return "Comune eliminato con successo";
	}
	
	@PostMapping("updateComunee")
	public String updateComune(@RequestBody Comune comune) {
		return comuneService.updateComune(comune.getId(), comune);
	}
	
	
	@GetMapping("/popola")
	public String popola() {
		File f = new File("src/main/resources/csv/comuni-italiani.csv");
		try {
			String string = FileUtils.readFileToString(f, "UTF-8");
			List<String> listaString=Arrays.asList(string.split("\\r?\\n"));
			for (String com : listaString) {				
				String[] c = com.split(";");
				comuneService.save(new Comune(c[2], provinciaService.getByNome(c[3])));
			}
			return "Caricamento comuni avvenuto";
		} catch (IOException e) {
			e.printStackTrace();
			return "errore nel caricamento comuni";
		}
	}
	
	@GetMapping("/existsByNome")
	public Boolean existsByNome(String nome) {
		return comuneService.existsByNome(nome);
	}
	
	@GetMapping("/getFirstByNome")
	public Comune getFirstByNome(String nome) {
		return comuneService.getByNome(nome);
	}
	
	
}
