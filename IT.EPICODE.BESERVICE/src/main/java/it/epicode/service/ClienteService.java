package it.epicode.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.model.Cliente;
import it.epicode.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRep;
	@Autowired
	IndirizzoService indirizzoService;
	
	
	public Boolean existsByName(String name) {
		return clienteRep.existsByRagioneSociale(name);
	}
	
	
	public Cliente getById(Long id) {
		return clienteRep.getById(id);
	}
	
	public String save(Cliente cliente) {
		
		clienteRep.save(cliente);
		return "salvataggio avvenuto";
	}
	
	
	
	public String delete(Long id) {
		Cliente cliente = (Cliente) clienteRep.getById(id);
		clienteRep.delete(cliente);
		return "cliente eliminato";
		
	}
	
	
		public String update(Long id, Cliente cliente) {
		Cliente clienteAgg = clienteRep.getById(id);
		clienteAgg.setRagioneSociale(cliente.getRagioneSociale());
		clienteAgg.setPartitaIva(cliente.getPartitaIva());
		clienteAgg.setTipoCliente(cliente.getTipoCliente());
		clienteAgg.setEmail(cliente.getEmail());
		clienteAgg.setPec(cliente.getPec());
		clienteAgg.setTelefono(cliente.getTelefono());
		clienteAgg.setNomeContatto(cliente.getNomeContatto());
		clienteAgg.setCognomeContatto(cliente.getCognomeContatto());
		clienteAgg.setTelefonoContatto(cliente.getTelefonoContatto());
		clienteAgg.setEmailContatto(cliente.getEmailContatto());
		clienteAgg.setIndirizzoSedeOperativa(cliente.getIndirizzoSedeOperativa());
		clienteAgg.setIndirizzoSedeLegale(cliente.getIndirizzoSedeLegale());
//		clienteAgg.setDataInserimento(cliente.getDataInserimento());
		clienteAgg.setDataUltimoContatto(cliente.getDataUltimoContatto());
		clienteAgg.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
		clienteRep.save(clienteAgg);
		return "cliente aggiornato";
		}

		public Cliente getByName(String name) {
			return clienteRep.getFirstByRagioneSociale(name);
		}
		
		
		/*
		 * **************************metodo per ordinare i clienti (sort = ragioneSociale /
		 * 																   fatturatoAnnuale/
		 * 																   dataInserimento /
		 * 															       dataUltimoContatto/
		 * 																   indirizzoSedeLegale_Comune_Provincia_Nome
		 */
		
		 public List<Cliente> findAll(Integer page, Integer size, String sort) {
		        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		        Page<Cliente> pagedResult = clienteRep.findAll(paging);       
		        if (pagedResult.hasContent()) {
		            return pagedResult.getContent();
		        } else {
		            return new ArrayList<Cliente>();
		        }
		    }
		 
		 
		 // ***************************************************metodi di ricerca per:
		 
		 
		 
		 //fatturatoAnnuale
		 
		 public List<Cliente> findByFatturatoAnnuale(Integer page, Integer size, String sort, Double fatturato){
			 Pageable paging = PageRequest.of(page, size, Sort.by(sort));
			 Page<Cliente> pagedResult = clienteRep.getByFatturatoAnnuale(paging, fatturato);
			 if (pagedResult.hasContent()) {
		            return pagedResult.getContent();
		        } else {
		            return new ArrayList<Cliente>();
		        }
		 }
		 
		 //dataInserimento
		 public List<Cliente> findByDataInserimento(Integer page, Integer size, String sort, LocalDate dataInserimento){
			 Pageable paging = PageRequest.of(page, size, Sort.by(sort));
			 Page<Cliente> pagedResult = clienteRep.getByDataInserimento(paging, dataInserimento);
			 if (pagedResult.hasContent()) {
		            return pagedResult.getContent();
		        } else {
		            return new ArrayList<Cliente>();
		        }
		 }
		 
		 
		 //dataUltimoContatto
		 public List<Cliente> findByDataUltimoContatto(Integer page, Integer size, String sort, LocalDate dataUltimoContatto){
			 Pageable paging = PageRequest.of(page, size, Sort.by(sort));
			 Page<Cliente> pagedResult = clienteRep.getByDataUltimoContatto(paging, dataUltimoContatto);
			 if (pagedResult.hasContent()) {
		            return pagedResult.getContent();
		        } else {
		            return new ArrayList<Cliente>();
		        }
		 }
		 
		 
		 //parte del nome
		 public List<Cliente> findByNomeContains(Integer page, Integer size, String sort, String ragioneSociale){
			 Pageable paging = PageRequest.of(page, size, Sort.by(sort));
			 Page<Cliente> pagedResult = clienteRep.getByRagioneSocialeContainsIgnoreCase(paging, ragioneSociale);
			 if (pagedResult.hasContent()) {
		            return pagedResult.getContent();
		        } else {
		            return new ArrayList<Cliente>();
		        }
		 }
}
