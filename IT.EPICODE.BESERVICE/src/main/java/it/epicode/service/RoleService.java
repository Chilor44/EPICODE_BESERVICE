package it.epicode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import it.epicode.model.Role;
import it.epicode.model.RoleType;
import it.epicode.repository.RoleRepository;

@Service
public class RoleService implements CommandLineRunner {

	@Autowired RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
//		roleRepository.deleteById(102L);
//		roleRepository.deleteById(103L);
//		Role user= new Role(RoleType.ROLE_USER);
//		Role admin= new Role(RoleType.ROLE_ADMIN);
//		
//		roleRepository.save(user);
//		roleRepository.save(admin);
		
	}

	public List<Role> myFindAllRole() {
        return roleRepository.findAll();
    }
	
	public void save(Role role) {
		roleRepository.save(role);
	}
	

}
