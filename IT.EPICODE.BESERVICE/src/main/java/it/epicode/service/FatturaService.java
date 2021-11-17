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

import it.epicode.model.Fattura;
import it.epicode.repository.FatturaRepository;

@Service
public class FatturaService {
	
	@Autowired
	FatturaRepository fatturaRep;
	
	
	public Boolean existsByNumero(Long numero) {
		return fatturaRep.existsByNumero(numero);
	}

	public String save(Fattura fattura) {
		fatturaRep.save(fattura);
		return "salvataggio avvenuto";
	}
	
	public String delete(Long id) {
		Fattura fattura = fatturaRep.getById(id);
		fatturaRep.delete(fattura);
		return "fattura eliminata";
		
	}
	
	public String update(Long id, Fattura fattura) {
		Fattura fatturaUpdate = fatturaRep.getById(id);
		fatturaUpdate.setData(fattura.getData());
		fatturaUpdate.setNumero(fattura.getNumero());
		fatturaUpdate.setAnno(fattura.getAnno());
		fatturaUpdate.setImporto(fattura.getImporto());
		fatturaUpdate.setStato(fattura.getStato());
		fatturaUpdate.setCliente(fattura.getCliente());
		fatturaRep.save(fatturaUpdate);
		return "fatttura aggiornata";
	}
	
	 public List<Fattura> findByClienteId(Integer page, Integer size, String sort, Long clienteId){
		 Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		 Page<Fattura> pagedResult = fatturaRep.getByClienteId(paging, clienteId);
		 if (pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Fattura>();
	        }
	 }
	 
	 
	 public List<Fattura> findByStato(Integer page, Integer size, String sort, String stato){
		 Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		 Page<Fattura> pagedResult = fatturaRep.getByStatoNome(paging, stato);
		 if (pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Fattura>();
	        }
	 }
	 

	 public List<Fattura> findByData(Integer page, Integer size, String sort, LocalDate data){
		 Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		 Page<Fattura> pagedResult = fatturaRep.getByData(paging, data);
		 if (pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Fattura>();
	        }
	 }
	 
	 public List<Fattura> findByAnno(Integer page, Integer size, String sort, Integer anno){
		 Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		 Page<Fattura> pagedResult = fatturaRep.getByAnno(paging, anno);
		 if (pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Fattura>();
	        }
	 }
	 
	 public List<Fattura> findByImportoBetween(Integer page, Integer size, String sort, Double importoMin, Double importoMax){
		 Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		 Page<Fattura> pagedResult = fatturaRep.getByImportoBetween(paging, importoMin, importoMax);
		 if (pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Fattura>();
	        }
	 }
	
}
