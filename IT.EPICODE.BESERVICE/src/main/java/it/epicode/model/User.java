package it.epicode.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import it.epicode.security.StringAttributeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User{    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String nome;

	/* Una volta definito il converter, è possibile annotare le proprietà che si intende cifrare, 
    in modo che il motore di persistenza JPA possa effettuare le operazioni di conversione */
    @Convert(converter = StringAttributeConverter.class)
    private String email;
    private Boolean active = true;    
    private String password;
    
    @ManyToMany
    @JoinTable(    name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


	public User(String username, String nome, String email, String password, boolean active) {
		this.username = username;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.active = active;
		
	}
	

	  public User(String username, String email, String password, String nome) {
	        super();
	        this.username = username;
	        this.email = email;
	        this.password = password;
	        this.nome = nome;
	       
	    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nome=" + nome + ", email=" + email + ", password="
				+ password + ", active=" + active + "]";
	}
	
	
}
