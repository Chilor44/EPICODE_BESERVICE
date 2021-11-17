package it.epicode.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import it.epicode.model.Cliente;
import it.epicode.model.Indirizzo;
import it.epicode.model.TipoCliente;
import it.epicode.service.ClienteService;
import it.epicode.service.ComuneService;
import it.epicode.service.IndirizzoService;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ClienteControllerTest {
	
	@Autowired
	ClienteService clienteService;
	@Autowired
	ComuneService comuneService;
	@Autowired
	IndirizzoService indirizzoService;
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	Cliente cliente;
	Indirizzo indirizzo1;
	Indirizzo indirizzo2;

	
	@Test
	
	void testZEliminaCliente() {
		indirizzo1 = new Indirizzo("via1", "civico1", "cap1", "localita1", comuneService.getComuneById(2L));
		indirizzo2 = new Indirizzo("via2", "civico2", "cap2", "localita2", comuneService.getComuneById(3L));	
		cliente = new Cliente("ragioneS1", "partitaIva", TipoCliente.SPA, "email", "pec", "telefono", "nomeContatto", "cognomeContatto", "telefonoContatto", "email", indirizzo1, indirizzo2, LocalDate.of(2021,05,06) , 1256D);
		indirizzoService.save(indirizzo1);
		indirizzoService.save(indirizzo2);
		clienteService.save(cliente);
		assertTrue(clienteService.existsByName("ragioneS1"));
		clienteService.delete(clienteService.getByName("ragioneS1").getId());
		assertFalse(clienteService.existsByName("ragioneS1"));
	}
	






}
