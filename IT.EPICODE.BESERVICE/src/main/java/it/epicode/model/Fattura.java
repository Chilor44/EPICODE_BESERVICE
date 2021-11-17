package it.epicode.model;

import java.time.LocalDate;

import javax.persistence.Column;
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
@Table (name = "fattura")
public class Fattura {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	@Column(unique = true)
	private String numero;
	private Integer anno;
	private Double importo;
	@ManyToOne
	private StatoFattura stato;
	@ManyToOne
	private Cliente cliente;
	
	
	public Fattura( LocalDate data, String numero, Double importo, StatoFattura stato, Cliente cliente) {
		super();
		
		this.data = data;
		this.numero = numero;
		this.anno = LocalDate.now().getYear();
		this.importo = importo;
		this.stato = stato;
		this.cliente = cliente; 
		
	}



	
	
}
