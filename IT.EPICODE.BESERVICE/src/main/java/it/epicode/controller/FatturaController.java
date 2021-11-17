package it.epicode.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.model.Fattura;
import it.epicode.model.StatoFattura;
import it.epicode.service.ClienteService;
import it.epicode.service.FatturaService;
import it.epicode.service.StatoFatturaService;

@RestController
@RequestMapping("/fatturaController")
public class FatturaController {

	@Autowired
	FatturaService fatturaService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	StatoFatturaService statoFatturaService;
	
	
	
	
	@PostMapping("salvaFattura")
	public String salvaFattura(@RequestBody Fattura fattura) {
		return fatturaService.save(fattura);
	}
	
	@PostMapping("eliminaFattura/{id}")
	public String eliminaFattura(@PathVariable (required = true) Long id) {
		return fatturaService.delete(id);
	}
	
	@PostMapping("updateFattura/{id}")
	public String updateFattura(@PathVariable Long id, @RequestBody Fattura fattura) {
		return fatturaService.update(id, fattura);
	}
	
	
	
	
	//********************** salvataggio con parametri
	
	@GetMapping("/savePageAdmin")
	public ModelAndView savepage(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, String numero, Double importo,
			String stato, String cliente) {
		ModelAndView model = new ModelAndView();
		Fattura f = new Fattura();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		String annoStr = data.format(formatter);
		Integer anno = Integer.parseInt(annoStr);
	
		f.setData(data);
		f.setNumero(numero);
		f.setImporto(importo);
		f.setAnno(anno);
		
		if(clienteService.existsByName(cliente)) {
		f.setCliente(clienteService.getByName(cliente));
		} else {
			model.setViewName("clienteNonPresente");
			return model;
		}
		
		
		if (!statoFatturaService.existsByNome(stato)) {
			StatoFattura sf = new StatoFattura(stato);
			statoFatturaService.save(sf);
		}
		f.setStato(statoFatturaService.getByNome(stato));
		try {
		fatturaService.save(f);
		} catch (Exception e) {
			model.setViewName("erroreSalvataggio");
			return model;
		}
		model.setViewName("gestioneFattureAdmin");
		return model;
	}
	
	// **************** metodi di ricerca
	
	
//	************* per ID ******************
	
	@GetMapping(value = "/getFatturaByClienteId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Fattura>> getFatturaById(@RequestParam(defaultValue = "0") Integer page,
    		@RequestParam(defaultValue = "50") Integer size, 
    		@RequestParam(defaultValue = "id") String sort, 
    		@RequestParam Long clienteId) {
      List<Fattura> list = fatturaService.findByClienteId(page, size, sort, clienteId);
      return new ResponseEntity<List<Fattura>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@GetMapping(value = "/getFatturaByClienteIdPage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getFatturaByClienteIdPage(@RequestParam(defaultValue = "0") Integer page,
    		@RequestParam(defaultValue = "50") Integer size, 
    		@RequestParam(defaultValue = "id") String sort, 
    		@RequestParam Long clienteId) {
	  List<Fattura> list = fatturaService.findByClienteId(page, size, sort, clienteId);
      ModelAndView model = new ModelAndView();
      model.addObject("fatture", list);
      model.setViewName("fattureListaEndpoint");
      return model; 
      }
	
	// ******************** per stato ***************
	
	
	@GetMapping(value = "/getFatturaByStato", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Fattura>> getFatturaById(@RequestParam(defaultValue = "0") Integer page, 
    		@RequestParam(defaultValue = "50") Integer size, @RequestParam(defaultValue = "stato") String sort,  @RequestParam String stato) {
      List<Fattura> list = fatturaService.findByStato(page, size, sort, stato);
      return new ResponseEntity<List<Fattura>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@GetMapping(value = "/getFatturaByStatoPage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getFatturaByIdPage(@RequestParam(defaultValue = "0") Integer page,
    		@RequestParam(defaultValue = "50") Integer size, @RequestParam(defaultValue = "stato") String sort,  @RequestParam String stato) {
      List<Fattura> list = fatturaService.findByStato(page, size, sort, stato);
      ModelAndView model = new ModelAndView();
      model.addObject("fatture", list);
      model.setViewName("fattureListaEndpoint");
      return model; 
    }
	
	
	// ****************** per data *********************
	
	@GetMapping(value = "/getFatturaByData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Fattura>> getFatturaByData(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size, @RequestParam(defaultValue = "data") String sort,  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
      List<Fattura> list = fatturaService.findByData(page, size, sort, data);
      return new ResponseEntity<List<Fattura>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@GetMapping(value = "/getFatturaByDataPage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getFatturaBydataPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size, @RequestParam(defaultValue = "data") String sort,  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
      List<Fattura> list = fatturaService.findByData(page, size, sort, data);
      ModelAndView model = new ModelAndView();
      model.addObject("fatture", list);
      model.setViewName("fattureListaEndpoint");
      return model; 
    }
	
	// ********************* per anno ********************
	
	@GetMapping(value = "/getFatturaByAnno", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Fattura>> getFatturaByAnno(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size, @RequestParam(defaultValue = "anno") String sort, @RequestParam Integer anno) {
      List<Fattura> list = fatturaService.findByAnno(page, size, sort, anno);
      return new ResponseEntity<List<Fattura>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@GetMapping(value = "/getFatturaByAnnoPage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getFatturaByAnnoPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size, @RequestParam(defaultValue = "anno") String sort, @RequestParam Integer anno) {
      List<Fattura> list = fatturaService.findByAnno(page, size, sort, anno);
      ModelAndView model = new ModelAndView();
      model.addObject("fatture", list);
      model.setViewName("fattureListaEndpoint");
      return model;
    }
	
	// ********************* per range di importo *******************
	
	@GetMapping(value = "/getFatturaByRange", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Fattura>> getFatturaByRange(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size, @RequestParam(defaultValue = "importo") String sort,
    		@RequestParam(defaultValue = "0") Double importoMin, @RequestParam(defaultValue = "100000000") Double importoMax) {
      List<Fattura> list = fatturaService.findByImportoBetween(page, size, sort, importoMin, importoMax);
      return new ResponseEntity<List<Fattura>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@GetMapping(value = "/getFatturaByRangePage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getFatturaByRangePage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size, @RequestParam(defaultValue = "importo") String sort,
    		@RequestParam(defaultValue = "0") Double importoMin, @RequestParam(defaultValue = "100000000") Double importoMax) {
      List<Fattura> list = fatturaService.findByImportoBetween(page, size, sort, importoMin, importoMax);
      ModelAndView model = new ModelAndView();
      model.addObject("fatture", list);
      model.setViewName("fattureListaEndpoint");
      return model;
    }
	
}
