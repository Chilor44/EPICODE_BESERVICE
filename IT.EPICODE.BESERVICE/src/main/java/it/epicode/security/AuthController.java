package it.epicode.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.model.Role;
import it.epicode.model.RoleType;
import it.epicode.model.User;
import it.epicode.repository.RoleRepository;
import it.epicode.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;

	@PostMapping("/signup1")
	public ResponseEntity<?> registerUser1(SignupRequest signUpRequest) { // @RequestBody
		// Verifica l'esistenza di Username e Email già registrate
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Errore: Username già in uso!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Errore: Email già in uso!"));
		}
		// Crea un nuovo user codificando la password
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getNome());
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		// Verifica l'esistenza dei Role
		if (strRoles == null) {
			Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByRoleType(RoleType.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new SignupResponse("User registrato con successo!"));

	}

	@SuppressWarnings("unused")
	@PostMapping("/login")
	public ModelAndView authenticateUser(LoginRequest loginRequest) {
		ModelAndView model = new ModelAndView();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));	
		authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		
		if (roles.contains("ROLE_ADMIN")) {
			model.setViewName("adminHome");
		} else {
			model.setViewName("userHome");
		}
		model.addObject(roles);
		return model;

	}

	@PostMapping("/signup")
	public ModelAndView registerUser(SignupRequest signUpRequest) { // @RequestBody

		ModelAndView model = new ModelAndView();
		// Verifica l'esistenza di Username e Email già registrate
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			model.setViewName("badRequest1");
			return model;
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			model.setViewName("badRequest");
			return model;
		}
		// Crea un nuovo user codificando la password
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getNome());
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		// Verifica l'esistenza dei Role
		if (strRoles == null) {
			Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByRoleType(RoleType.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		Role admin = roleRepository.findByRoleType(RoleType.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));

		if (user.getRoles().contains(admin)) {
			model.setViewName("adminHome");
		} else {
			model.setViewName("userHome");
		}

		return model;
	}
	

	
//	@PostMapping("/login") 
//	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) { 
//	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())); 
	// Ottiene i privilegi dell'utente
//			  authentication.getAuthorities();
//			  
//			  // Ottiene il SecurityContext
//			  SecurityContextHolder.getContext().setAuthentication(authentication); //
//			  Genera il token String jwt = jwtUtils.generateJwtToken(authentication);
//			  
//			  // getPrincipal(), ottiene i dati dell'utente UserDetailsImpl userDetails =
//			  (UserDetailsImpl) authentication.getPrincipal(); List<String> roles =
//			  userDetails.getAuthorities().stream().map(item ->
//			  item.getAuthority()).collect(Collectors.toList());
//			  
//			  // Restituisce la response return ResponseEntity.ok(new LoginResponse(jwt,
//			  userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
//			  roles, userDetails.getExpirationTime())); }

}


