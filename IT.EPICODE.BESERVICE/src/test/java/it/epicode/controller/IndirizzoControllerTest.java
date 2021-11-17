package it.epicode.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import it.epicode.model.Indirizzo;
import it.epicode.service.ComuneService;
import it.epicode.service.IndirizzoService;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class IndirizzoControllerTest {
	@LocalServerPort
	private int port=8080;
	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	ComuneService comuneService;
	@Autowired
	IndirizzoService indirizzoService;
	
	@Test
	void testSalvaIndirizzo() {
		indirizzoService.save(new Indirizzo("via Archimede","90","20129","Milano",comuneService.getByNome("Milano")));
		assertTrue(indirizzoService.existsByVia("via Archimede"));	}

}
	


