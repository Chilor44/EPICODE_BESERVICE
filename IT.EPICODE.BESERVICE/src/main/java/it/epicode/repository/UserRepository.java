
package it.epicode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.model.User;


@Component
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.nome = :nome" )
	List<User> findbyName(String nome);
	
//	@Query("SELECT u FROM User u WHERE u.username = :username" )
//	List<User> findbyUserName(String username);
	  /* Sort */
    // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
	
    List<User> findByOrderByUsernameDesc();
	
	public Page<User> findAll(Pageable pageable);
	 public List<User> findByOrderByNomeAsc();
	 
	 Optional<User> findByUsername(String nome);
	 
		Boolean existsByUsername(String username);
	    Boolean existsByEmail(String email);
}
