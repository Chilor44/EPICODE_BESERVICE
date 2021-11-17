package it.epicode.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "indirizzo")
public class Indirizzo {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String via;
	private String civico;
	private String cap;
	private String localita;
	@ManyToOne
	private Comune comune;
	
	public Indirizzo(String via, String civico, String cap, String localita, Comune comune) {
		super();
		this.via = via;
		this.civico = civico;
		this.cap = cap;
		this.localita = localita;
		this.comune = comune;
	}

	@Override
	public String toString() {
		return "Indirizzo: " + via + ", " + civico + ", " + cap + ", località: " + localita ;
	}
	
	
	
}
