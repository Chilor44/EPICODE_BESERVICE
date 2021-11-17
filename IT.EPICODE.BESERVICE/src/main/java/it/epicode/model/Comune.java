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
@Table (name = "comune")
public class Comune {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToOne 
	private Provincia provincia;
	
	
	public Comune(String nome, Provincia provincia) {
		super();
		this.nome = nome;
		this.provincia = provincia;
	}
	
	
}

/*(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH,
			CascadeType.DETACH}, fetch = FetchType.EAGER )
 * 
*/