package it.epicode.controller;


import java.time.LocalDate;
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

import it.epicode.model.Cliente; 
import it.epicode.model.TipoCliente;
import it.epicode.service.ClienteService;
import it.epicode.service.ComuneService;
import it.epicode.service.IndirizzoService;

@RestController
@RequestMapping("/clienteController")
public class ClienteController {

	@Autowired
	ClienteService clienteServ;
	@Autowired
	IndirizzoService indirizzoService;
	@Autowired
	ComuneService comuneService;
	
	
	
	
	@PostMapping("salvaCliente")
	public String salvaCliente(@RequestBody Cliente cliente) {
		cliente.setDataInserimento(LocalDate.now());
		return clienteServ.save(cliente);
	}
		
	
	@PostMapping("eliminaCliente/{id}")
	public String eliminaCliente(@PathVariable (required = true) Long id) {
		return clienteServ.delete(id);
	}
	
	@PostMapping("updateCliente/{id}")
	public String updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		return clienteServ.update(id, cliente);
	}
	
	
	/*
	 * **************************metodo per ordinare i clienti (sort = ragioneSociale /
	 * 																   fatturatoAnnuale/
	 * 																   dataInserimento /
	 * 															       dataUltimoContatto/
	 * 																   indirizzoSedeLegale_Comune_Provincia_Nome )
	 */
    
	
	@GetMapping(value = "/getAllCliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getAllCliente(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "id") String sort) {
      List<Cliente> list = clienteServ.findAll(page, size, sort);
      return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    
	
	@GetMapping(value = "/getAllClientePage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllClientePage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "id") String sort) {
      List<Cliente> list = clienteServ.findAll(page, size, sort);
     ModelAndView model = new ModelAndView();
     model.addObject("clienti", list);
     model.setViewName("clientiListaEndpoint");
      return model;
      }
	
	@GetMapping(value = "/getAllClientePageAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllClientePageAdmin(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "id") String sort) {
      List<Cliente> list = clienteServ.findAll(page, size, sort);
     ModelAndView model = new ModelAndView();
     model.addObject("clienti", list);
     model.setViewName("clientiListaEndpointAdmin");
      return model;
      }
	
	// *******************************metodi di ricerca per:
    
	
	//fatturatoAnnuale
	
    @GetMapping(value = "/getClienteByFatturatoAnnuale", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getAllClienteByFatturato(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "fatturatoAnnuale") String sort,  @RequestParam Double fatturatoAnnuale) {
      List<Cliente> list = clienteServ.findByFatturatoAnnuale(page, size, sort, fatturatoAnnuale);
      return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    
    @GetMapping(value = "/getClienteByFatturatoAnnualePage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllClienteByFatturatoPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "fatturatoAnnuale") String sort,  @RequestParam Double fatturatoAnnuale) {
      List<Cliente> list = clienteServ.findByFatturatoAnnuale(page, size, sort, fatturatoAnnuale);
      ModelAndView model = new ModelAndView();
      model.addObject("clienti", list);
      model.setViewName("clientiListaEndpoint");
       return model; }
   
    @GetMapping(value = "/getClienteByFatturatoAnnualePageAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllClienteByFatturatoPageAdmin(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "fatturatoAnnuale") String sort,  @RequestParam Double fatturatoAnnuale) {
      List<Cliente> list = clienteServ.findByFatturatoAnnuale(page, size, sort, fatturatoAnnuale);
      ModelAndView model = new ModelAndView();
      model.addObject("clienti", list);
      model.setViewName("clientiListaEndpointAdmin");
       return model; }
    
    //dataInserimento
    
    @GetMapping(value = "/getClienteByDataInserimento", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getAllClienteByDataInserimento(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "dataInserimento") String sort, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento) {
      List<Cliente> list = clienteServ.findByDataInserimento(page, size, sort, dataInserimento);
      return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    
    @GetMapping(value = "/getClienteByDataInserimentoPage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllClienteByDataInserimentoPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "dataInserimento") String sort, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento) {
      List<Cliente> list = clienteServ.findByDataInserimento(page, size, sort, dataInserimento);
      ModelAndView model = new ModelAndView();
      model.addObject("clienti", list);
      model.setViewName("clientiListaEndpoint");
      return model; 
       } 
    
    @GetMapping(value = "/getClienteByDataInserimentoPageAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllClienteByDataInserimentoPageAdmin(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "dataInserimento") String sort, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento) {
      List<Cliente> list = clienteServ.findByDataInserimento(page, size, sort, dataInserimento);
      ModelAndView model = new ModelAndView();
      model.addObject("clienti", list);
      model.setViewName("clientiListaEndpointAdmin");
      return model; 
       }
    
    //dataUltimoContatto
    
    @GetMapping(value = "/getClienteByDataUltimoContatto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getAllClienteByDataUltimoContatto(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "dataInserimento") String sort, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto) {
      List<Cliente> list = clienteServ.findByDataUltimoContatto(page, size, sort, dataUltimoContatto);
      return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    
    @GetMapping(value = "/getClienteByDataUltimoContattoPage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllClienteByDataUltimoContattoPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "dataInserimento") String sort, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto) {
      List<Cliente> list = clienteServ.findByDataUltimoContatto(page, size, sort, dataUltimoContatto);
      ModelAndView model = new ModelAndView();
      model.addObject("clienti", list);
      model.setViewName("clientiListaEndpoint");
      return model; 
      }
    
    @GetMapping(value = "/getClienteByDataUltimoContattoPageAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllClienteByDataUltimoContattoPageAdmin(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "dataInserimento") String sort, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto) {
      List<Cliente> list = clienteServ.findByDataUltimoContatto(page, size, sort, dataUltimoContatto);
      ModelAndView model = new ModelAndView();
      model.addObject("clienti", list);
      model.setViewName("clientiListaEndpointAdmin");
      return model; 
      }

    
    // *******************************parte del nome ***********************
    
    @GetMapping(value = "/getClienteByNomeContains", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getAllClienteParteDelNome(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "ragioneSociale") String sort,  @RequestParam String ragioneSociale) {
      List<Cliente> list = clienteServ.findByNomeContains(page, size, sort, ragioneSociale);
      return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
    
    
    
    @GetMapping(value = "/getClienteByNomeContainsPage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllClienteParteDelNomePage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "ragioneSociale") String sort,  @RequestParam String ragioneSociale) {
      List<Cliente> list = clienteServ.findByNomeContains(page, size, sort, ragioneSociale);
      ModelAndView model = new ModelAndView();
      model.addObject("clienti", list);
      model.setViewName("clientiListaEndpoint");
      return model; 
      }
    
    @GetMapping(value = "/getClienteByNomeContainsPageAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getAllClienteParteDelNomePageAdmin(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size, @RequestParam(defaultValue = "ragioneSociale") String sort,  @RequestParam String ragioneSociale) {
      List<Cliente> list = clienteServ.findByNomeContains(page, size, sort, ragioneSociale);
      ModelAndView model = new ModelAndView();
      model.addObject("clienti", list);
      model.setViewName("clientiListaEndpointAdmin");
      return model; 
      }
    
    
    //********************** metodi con param per HTML ****************************************
    
    
	@GetMapping("salvaClienteParam")
	public String salvaClienteParam(@RequestParam String ragioneSociale, String partitaIva, TipoCliente tipoCliente, String email, String pec,
			String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto, String emailContatto,
			String via1, String civico1, String cap1, String localita1, String nomeComune1, String via2, String civico2, String cap2, String localita2, String nomeComune2, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto,
			Double fatturatoAnnuale) {
		
		Cliente cliente=new Cliente();
		indirizzoService.controlloIndirizzo(via1, civico1, cap1, localita1, nomeComune1); //salva l'indirizzo se non è ancora presente su db
		indirizzoService.controlloIndirizzo(via2, civico2, cap2, localita2, nomeComune2);
		
		cliente.setRagioneSociale(ragioneSociale);
		cliente.setPartitaIva(partitaIva);
		cliente.setTipoCliente(tipoCliente);
		cliente.setEmail(emailContatto);
		cliente.setPec(pec);
		cliente.setTelefono(telefono);
		cliente.setNomeContatto(nomeContatto);
		cliente.setCognomeContatto(cognomeContatto);
		cliente.setTelefonoContatto(telefonoContatto);
		cliente.setEmailContatto(emailContatto);
		cliente.setIndirizzoSedeOperativa(indirizzoService.getByVia(via1));
		cliente.setIndirizzoSedeOperativa(indirizzoService.getByVia(via2));
		cliente.setDataInserimento(LocalDate.now());
		cliente.setDataUltimoContatto(dataUltimoContatto);
		cliente.setFatturatoAnnuale(fatturatoAnnuale);
		
		clienteServ.save(cliente);
		
		return "";
		}
	
	
	
	
	@GetMapping("creaModificaClientePage")
	public ModelAndView salvaClientePage(@RequestParam String ragioneSociale, String partitaIva, TipoCliente tipoCliente, String email, String pec,
			String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto, String emailContatto,
			String via1, String civico1, String cap1, String localita1, String nomeComune1, String via2, String civico2, String cap2, String localita2,
			String nomeComune2, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto,
			Double fatturatoAnnuale) {
		
		ModelAndView model = new ModelAndView();
		
		if (!clienteServ.existsByName(ragioneSociale)){
		salvaClienteParam(ragioneSociale, partitaIva, tipoCliente, email, pec, telefono, nomeContatto, cognomeContatto, telefonoContatto, emailContatto, via1, civico1, cap1, localita1, nomeComune1, via2, civico2, cap2, localita2, nomeComune2, dataUltimoContatto, fatturatoAnnuale);
		model.setViewName("gestioneClientiAdmin");
		return model;
		} 
		
		else {
				
		indirizzoService.controlloIndirizzo(via1, civico1, cap1, localita1, nomeComune1);
		indirizzoService.controlloIndirizzo(via2, civico2, cap2, localita2, nomeComune2);
		Cliente cliente= new Cliente(ragioneSociale, partitaIva, tipoCliente, email, pec, telefono,
				nomeContatto, cognomeContatto, telefonoContatto, emailContatto, 
				indirizzoService.getByVia(via1), indirizzoService.getByVia(via2),
				dataUltimoContatto, fatturatoAnnuale);
		clienteServ.update(clienteServ.getByName(ragioneSociale).getId(), cliente);
		}
		
		model.setViewName("gestioneClientiAdmin");
		return model;
	
	}
	
	
	@GetMapping("/clienteEdit/{id}")
	public ModelAndView editCliente(@PathVariable Long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("cliente", clienteServ.getById(id));
		model.setViewName("editCliente");
		return model;
	}
	
//	@GetMapping("{cid}/addresses/postal/edit/{id}")
//	public ModelAndView updateAddress(@PathVariable long cid, @PathVariable long id, ModelAndView model) {
//		var contact = service.read(cid).get();
//		var address = contact.getAddresses().stream().filter(a -> a.getId() == id).findAny().orElseThrow();
//		model.addObject("address", address);
//		model.addObject("contact", contact);
//		model.setViewName("/addresses/postal/edit");
//		return model;
//	}
	
	@GetMapping("/clienteEdit")
	public ModelAndView editArticle(ModelAndView model, @RequestParam String ragioneSociale, String partitaIva, TipoCliente tipoCliente, String email, String pec,
			String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto, String emailContatto,
			String via1, String civico1, String cap1, String localita1, String nomeComune1, String via2, String civico2,
			String cap2, String localita2, String nomeComune2, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto,
			Double fatturatoAnnuale) {
		
		indirizzoService.controlloIndirizzo(via1, civico1, cap1, localita1, nomeComune1);
		indirizzoService.controlloIndirizzo(via2, civico2, cap2, localita2, nomeComune2);
		Cliente clienteAgg= new Cliente(ragioneSociale, partitaIva, tipoCliente, email, pec, telefono,
				nomeContatto, cognomeContatto, telefonoContatto, emailContatto, 
				indirizzoService.getByVia(via1), indirizzoService.getByVia(via2),
				dataUltimoContatto, fatturatoAnnuale);
		Cliente cliente = (Cliente) (model.getModel().get("cliente"));
		clienteServ.update(cliente.getId(), clienteAgg);
		return getAllClientePageAdmin(0, 100, "id");
	}
	
	
	@GetMapping("/clienteDetailsAdmin/{id}")
	public ModelAndView getclienteDetails(@PathVariable Long id) {
		ModelAndView myModel=new ModelAndView();
		Cliente cliente=clienteServ.getById(id);
		myModel.addObject("cliente", cliente);
		myModel.setViewName("clienteDetailsAdmin");
		return myModel;
		}
}
	
	
	
	
	
	
	

