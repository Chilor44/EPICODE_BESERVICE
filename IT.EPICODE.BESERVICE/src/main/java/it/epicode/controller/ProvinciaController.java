package it.epicode.controller;





import java.io.File;
import java.io.IOException;
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

import it.epicode.model.Provincia;
import it.epicode.service.ProvinciaService;

@RestController
@RequestMapping("/provinciaController")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;
	
	@GetMapping("/popola")
	public String popola() {
		File f = new File("src/main/resources/csv/province-italiane.csv");
		try {
			String string = FileUtils.readFileToString(f, "UTF-8");
			List<String> listaString=Arrays.asList(string.split("\r\n"));
			for (String prov : listaString) {
				String[] s = prov.split(";");
				Provincia p = new Provincia(s[1],s[0]);
				provinciaService.save(p);
			}
			return "Caricamento province avvenuto";
		} catch (IOException e) {
			e.printStackTrace();
			return "Errore nel caricamento province";
		}
	}
	
	@PostMapping("salvaProvincia")
	public String salvaProvincia(@RequestBody Provincia provincia) {
		return provinciaService.save(provincia);
	}
	
	@PostMapping("eliminaProvincia/{id}")
	public String eliminaProvincia(@PathVariable (required = true) Long id) {
		return provinciaService.delete(id);
	}
	
	@PostMapping("updateProvincia")
	public String updateProvincia(@RequestBody Provincia provincia) {
		return provinciaService.update(provincia.getId(), provincia);
	}
}

	
	
