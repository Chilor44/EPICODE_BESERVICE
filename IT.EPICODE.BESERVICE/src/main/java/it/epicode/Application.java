package it.epicode;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.epicode.controller.ComuneController;
import it.epicode.controller.ProvinciaController;
import it.epicode.model.Cliente;
import it.epicode.model.Fattura;
import it.epicode.model.Indirizzo;
import it.epicode.model.StatoFattura;
import it.epicode.model.TipoCliente;
import it.epicode.service.ClienteService;
import it.epicode.service.ComuneService;
import it.epicode.service.FatturaService;
import it.epicode.service.IndirizzoService;
import it.epicode.service.StatoFatturaService;

@SpringBootApplication
public class Application {


	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		//popolaDB();
		
		
		
	}
	
	
	
//	public static void popolaDB() {
//		
//			
//		
//		IndirizzoService indirizzoService = new IndirizzoService();
//		ClienteService clienteService = new ClienteService();
//	ComuneService comuneService = new ComuneService();
//		StatoFatturaService statoFattService = new StatoFatturaService();
//		FatturaService fc = new FatturaService();
//		StatoFatturaService sfc = new StatoFatturaService();
//		
//	
//}
//		
//		
//		Indirizzo indirizzo8  = new Indirizzo("Via Giovanni Amendola", " 84", "26010", "Periferia",comuneService.getByNome("Ripalta Guerina"));
//		Indirizzo indirizzo9  = new Indirizzo("Via Santa Maria di Costantinopoli", "135", "46040", "marittima",comuneService.getByNome("Rodigo"));
//		Indirizzo indirizzo10 = new Indirizzo("Via Francesco Del Giudice", "161", "50030", "montagna",comuneService.getByNome("Pancarana"));
//		Indirizzo indirizzo11 = new Indirizzo("Via Duomo", "139", "57020", "centro", comuneService.getByNome("Pelago"));
//		Indirizzo indirizzo12 = new Indirizzo("Via Antonio da Legnago", "37", "89010", "marittima", comuneService.getByNome("Cannara"));
//		Indirizzo indirizzo13 = new Indirizzo("Via Nazario Sauro", "156", "20012", "centro", comuneService.getByNome("Cornaredo"));
//		Indirizzo indirizzo14 = new Indirizzo("Via Croce Rossa", "176", "09070", "centro", comuneService.getByNome("Narbolia"));
//		Indirizzo indirizzo15 = new Indirizzo("Via Nazario Sauro", "110", "20050", "Periferia", comuneService.getByNome("Correzzana"));
//		Indirizzo indirizzo16 = new Indirizzo("Piazza Concordia", "5", "43059", "Tornolo", comuneService.getByNome("Tornolo"));
//		Indirizzo indirizzo17 = new Indirizzo("Via Lago Febo", "113", "25049", "Iseo", comuneService.getByNome("Iseo"));
//		Indirizzo indirizzo18 = new Indirizzo("Via Firense", "174", "86074", "Filignano", comuneService.getByNome("Filignano"));
//		Indirizzo indirizzo19 = new Indirizzo("Piazza Guglielmo Pepe", "96", "62011", "San Vittore", comuneService.getByNome("San Vittore Olona"));
//		Indirizzo indirizzo20 = new Indirizzo("Via Castelfidardo", "151", "87030", "Carolei", comuneService.getByNome("Carolei"));
//		
//	
//		
//		Cliente cliente1 = new Cliente("Geeksolution", "6516541656", TipoCliente.PA, "geeksolution@email.com", "geeksolution@pec.com",
//				"717853", "Alice", "Pantaleone", "42375378573", "alice@email.com", indirizzo8, indirizzo9, LocalDate.of(2018, 7, 7), 322514.00);
//		Cliente cliente2 = new Cliente("Weber Gabriele", "654165462", TipoCliente.SPA, "wg@email.com", "wg@pec.com", 
//				"124352", "Espedito", "Calabresi", "537537", "calabresi@email.com", indirizzo9, indirizzo10, LocalDate.of(2020, 2, 15), 124563.00);
//		Cliente cliente3 = new Cliente("Italiaonline Web Agency", "5864454646", TipoCliente.SRL, "iwa@email.com", "iwa@pec.com",
//				"15052100", "Napoleone", "Udinese", "7357537", "napoleons@email.com", indirizzo11, indirizzo12, LocalDate.of(2021, 10, 3), 123458.00);
//		Cliente cliente4 = new Cliente("Dunder Mifflin", "1234569", TipoCliente.SAS, "ddm@email.com", "ddm@pec.com", 
//				"56165656", "Micheal", "Scott", "2646465", "m.scott@email.com", indirizzo13, indirizzo15, LocalDate.of(2020, 10, 3), 200000.05);	
//		Cliente cliente5 = new Cliente("3M", "263197469", TipoCliente.PA, "3M@gmail.com", "3M@pec.com", "06125873", "Imelda",
//				"Rossi", "3537516110", "imelda@gmail.com", indirizzo16, indirizzo17, LocalDate.of(2021, 11, 14), 220000.0);
//		Cliente cliente6 = new Cliente("Daewoo", "2762583", TipoCliente.SPA, "daewoo@gmail.com", "daewoo@pec.com", 
//				"0636934", "Emma", "Pisano", "3712578531", "emma@gmail.com", indirizzo18, indirizzo19, LocalDate.of(2021, 11, 27), 450000.0);
//		Cliente cliente7 = new Cliente("Sgarbi Digital Marketing", "2645668546", TipoCliente.SAS, "sdm@email.com", "sdm@pec.com",
//				"87635411", "Galileo", "Napolitani", "3427375", "galileo@email.com", indirizzo20, indirizzo8, LocalDate.of(2020, 6, 30), 234526.00);
//		Cliente cliente8 = new Cliente("Lpe Production", "5445416512", TipoCliente.SPA, "lpe@email.com", "lpe@pec.com",
//				"3457833", "Ottavio", "Lettiere", "21343757", "ottavio@email.com", indirizzo9, indirizzo8, LocalDate.of(2021, 5, 5), 125336.00);
//		Cliente cliente9 = new Cliente("Alea Spa", "IT 04338490404", TipoCliente.SPA, "info@alea.it", "alea@PEC.it",
//				"054103425", "Elena", "Rossi", "3402587649","elenarossi@alea.it", indirizzo10, indirizzo9, LocalDate.of(2021, 05, 15), 150574.00);
//		Cliente cliente10 = new Cliente("Edison Spa", "IT 78526395412", TipoCliente.SPA, "info@edison.it", "edison@PEC.it",
//				"087094536", "Elia", "Viesi", "3408956421","eliaviesi@edison.it", indirizzo12, indirizzo11, LocalDate.of(2020, 10, 28), 7852634.00);
//		Cliente cliente11 = new Cliente("ABB Spa", "IT 04330404849", TipoCliente.SPA, "info@abb.it", "abb@PEC.it",
//				"0463258713", "Marta", "Verdi", "3474425613","martaverdi@abb.it", indirizzo14, indirizzo13, LocalDate.of(2021, 07, 14), 7895263.00);
//		Cliente cliente12 = new Cliente("TaoSystem Srl", "IT 04334045826", TipoCliente.SRL, "info@taosystem.it", "taosystem@PEC.it",
//				"0562034598", "Alessia", "Lorenzi", "3485206392","alessialorenzi@taosystem.it", indirizzo15, indirizzo14, LocalDate.of(2021, 01, 15), 1007404.00);
//		Cliente cliente13 = new Cliente("Pam Srl", "IT 84908485942", TipoCliente.SPA, "info@pam.it", "pam@PEC.it",
//				"0899254166", "Marco", "Molinari", "3201148695","marcomolinari@pam.it", indirizzo17, indirizzo18, LocalDate.of(2020, 05, 06), 571504.00);
//		Cliente cliente14 = new Cliente("Pied Piper Spa", "IT 85649321574", TipoCliente.SPA, "info@piedpiper.it", "piedpiper@PEC.it",
//				"0899254166", " Bertram", "Gilfoyle", "3201148695","bertramgilfoyle@piedpiper.it", indirizzo8, indirizzo20, LocalDate.of(2020, 05, 06), 571504.00);
//		Cliente cliente15 = new Cliente("Thermosystem Spa", "IT 45875458213", TipoCliente.SPA, "info@thermosystem.it", "thermosystem@PEC.it",
//				"0899254166", "Dinesh", "Chugtai", "3201148695","dineshchugtai@thermosystem.it", indirizzo19, indirizzo15, LocalDate.of(2020, 05, 06), 571504.00);
//	
//		
//		
//		
//		
//		
//		
//		StatoFattura sf=new StatoFattura("SALDATA");
//		StatoFattura sf1=new StatoFattura("DA SALDARE");
//		sfc.save(sf);
//		sfc.save(sf1);
//		
//		
//		Fattura f1 = new Fattura(LocalDate.of(2021, 10, 15), "123456", 2000.00, sf, cliente1);
//	    Fattura f2 = new Fattura(LocalDate.of(2020, 2, 24), "123432", 300.00, sf1, cliente1);
//	    Fattura f3 = new Fattura(LocalDate.of(2021, 7, 15), "9655", 970.12, sf, cliente2);
//	    Fattura f4 = new Fattura(LocalDate.of(2021, 3, 2), "6548", 10450.00, sf1, cliente2);
//	    Fattura f5 = new Fattura(LocalDate.of(2018, 1, 16), "123456", 12345.00, sf, cliente3);
//	    Fattura f6 = new Fattura(LocalDate.of(2021, 7, 30), "54654", 3651.00, sf1, cliente3);
//	    Fattura f7 = new Fattura(LocalDate.of(2012, 7, 4), "566516", 326.00, sf, cliente4);
//	    Fattura f8 = new Fattura(LocalDate.of(2014, 11, 9), "21561", 7532.00, sf1, cliente4);
//	    Fattura f9 = new Fattura(LocalDate.of(2021, 3, 16), "31516", 1245.00, sf1, cliente5);
//	    Fattura f10 = new Fattura(LocalDate.of(2018, 10, 18), "1564", 5465.00, sf, cliente5);
//	    Fattura f11 = new Fattura(LocalDate.of(2020, 7, 15), "23565", 3565.00, sf1, cliente6);
//	    Fattura f12 = new Fattura(LocalDate.of(2021, 3, 20), "94565", 102.00, sf, cliente6);
//	    Fattura f13 = new Fattura(LocalDate.of(2021, 11, 25), "1256", 320.00, sf, cliente7);
//	    Fattura f14 = new Fattura(LocalDate.of(2021, 8, 5), "12345", 420.00, sf, cliente7);
//	    Fattura f15 = new Fattura(LocalDate.of(2020, 4, 24), "24235", 7546.00, sf, cliente8);
//	    Fattura f16 = new Fattura(LocalDate.of(2018, 12, 31), "24557", 12125.00, sf, cliente8);
//	    Fattura f17 = new Fattura(LocalDate.of(2021, 4, 7), "45783", 2230.00, sf1, cliente9);
//	    Fattura f18 = new Fattura(LocalDate.of(2021, 6, 3), "47537", 547.00, sf1, cliente9);
//	    Fattura f19 = new Fattura(LocalDate.of(2019, 3, 12), "354165", 22000.00, sf, cliente10);
//	    Fattura f20 = new Fattura(LocalDate.of(2020, 7, 15), "15166", 1245.00, sf1, cliente10);
//	    Fattura f21 = new Fattura(LocalDate.of(2021, 3, 4), "24276", 422.00, sf1, cliente11);
//	    Fattura f22 = new Fattura(LocalDate.of(2021, 4, 21), "2738", 412583.00, sf, cliente11);
//	    Fattura f23 = new Fattura(LocalDate.of(2021, 12, 15), "154556", 3400.00, sf, cliente12);
//	    Fattura f24 = new Fattura(LocalDate.of(2021, 10, 24), "36556", 2200.00, sf, cliente12);
//	    Fattura f25 = new Fattura(LocalDate.of(2018, 7, 24), "7377", 75564.00, sf, cliente13);
//	    Fattura f26 = new Fattura(LocalDate.of(2019, 9, 12), "12357", 42.00, sf1, cliente13);
//	    Fattura f27 = new Fattura(LocalDate.of(2021, 7, 15), "123456", 2000.00, sf, cliente14);
//	    Fattura f28 = new Fattura(LocalDate.of(2020, 8, 20), "123456", 2000.00, sf, cliente14);
//	    Fattura f29 = new Fattura(LocalDate.of(2021, 9, 4), "456", 31235.00, sf1, cliente15);
//	    Fattura f30 = new Fattura(LocalDate.of(2021, 10, 6), "56", 3225.00, sf, cliente15);
//	    Fattura f31 = new Fattura(LocalDate.of(2028, 3, 21), "121", 545.00, sf, cliente1);
//	    Fattura f32 = new Fattura(LocalDate.of(2021, 5, 23), "856", 5.00, sf, cliente2);
//	    Fattura f33 = new Fattura(LocalDate.of(2022, 9, 9), "48946", 6584.00, sf, cliente3);
//	    Fattura f34 = new Fattura(LocalDate.of(2024, 3, 4), "1", 1235.00, sf, cliente4);
//	    Fattura f35 = new Fattura(LocalDate.of(2021, 10, 30), "5166", 230.00, sf, cliente5);
//	    Fattura f36 = new Fattura(LocalDate.of(2021, 3, 9), "10", 36.00, sf, cliente6);
//	    Fattura f37 = new Fattura(LocalDate.of(2021, 6, 3), "13", 256.00, sf, cliente7);
//	    Fattura f38 = new Fattura(LocalDate.of(2021, 11, 15), "163", 986.00, sf, cliente8);
//	    Fattura f39 = new Fattura(LocalDate.of(2021, 12, 25), "123456", 2000.00, sf, cliente9);
//	    Fattura f40 = new Fattura(LocalDate.of(2021, 12, 31), "123456", 2000.00, sf, cliente10);
//	    fc.save(f1);
//	    fc.save(f2);
//	    fc.save(f3);
//	    fc.save(f4);
//	    fc.save(f5);
//	    fc.save(f6);
//	    fc.save(f7);
//	    fc.save(f8);
//	    fc.save(f9);
//	    fc.save(f10);
//	    fc.save(f11);
//	    fc.save(f12);
//	    fc.save(f13);
//	    fc.save(f14);
//	    fc.save(f15);
//	    fc.save(f16);
//	    fc.save(f17);
//	    fc.save(f18);
//	    fc.save(f19);
//	    fc.save(f20);
//	    fc.save(f21);
//	    fc.save(f22);
//	    fc.save(f23);
//	    fc.save(f24);
//	    fc.save(f25);
//	    fc.save(f26);
//	    fc.save(f27);
//	    fc.save(f28);
//	    fc.save(f29);
//	    fc.save(f30);
//	    fc.save(f31);
//	    fc.save(f32);
//	    fc.save(f33);
//	    fc.save(f34);
//	    fc.save(f35);
//	    fc.save(f36);
//	    fc.save(f37);
//	    fc.save(f38);
//	    fc.save(f39);
//	    fc.save(f40);
//		
//		
//	    return "Database popolato";
//
//	}

}
