package it.epicode.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import it.epicode.model.StatoFattura;
import it.epicode.service.StatoFatturaService;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StatoFatturaControllerTest {

	
	@Autowired
	StatoFatturaService statoFatturaService;

	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	void testUpdateStatoFattura() {
		StatoFattura statoFattura = new StatoFattura("DA SALDARE");
		statoFatturaService.save(statoFattura);
		statoFattura.setNome("MODIFICATA");
		statoFatturaService.update(statoFatturaService.getByNome("DA SALDARE").getId(), statoFattura);
		assertTrue(statoFatturaService.existsByNome("MODIFICATA"));
		
	}

}
