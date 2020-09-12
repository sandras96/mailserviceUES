package com.myproject.mailservice.controller;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mailservice.dto.UserDTO;
import com.myproject.mailservice.entity.Authority;
import com.myproject.mailservice.entity.User;
import com.myproject.mailservice.entity.UserTokenState;
import com.myproject.mailservice.security.JwtAuthenticationRequest;
import com.myproject.mailservice.security.TokenHelper;
import com.myproject.mailservice.service.AuthorityInterface;
import com.myproject.mailservice.service.CustomUserDetailsService;
import com.myproject.mailservice.service.UserInterface;




@RestController
@RequestMapping(value = "api/auth")
@CrossOrigin("*")
public class AuthenticationController {

	 @Autowired
	    TokenHelper tokenHelper;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private CustomUserDetailsService userDetailsService;
	    
	    @Autowired
		private UserInterface userService;
	    
	    @Autowired
		private AuthorityInterface asi; 
	    
		@Autowired
		private PasswordEncoder passwordencoder;


	    @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public ResponseEntity<?> createAuthenticationToken(
	            @RequestBody JwtAuthenticationRequest authenticationRequest,
	            HttpServletResponse response
	    ) throws AuthenticationException, IOException {
	    	
	        // Izvrsavanje security dela
	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        authenticationRequest.getUsername(),
	                        authenticationRequest.getPassword()
	                )
	        );

	        // Ubaci username + password u kontext
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Kreiraj token
	        User user = (User)authentication.getPrincipal();
	        String jws = tokenHelper.generateToken( user.getUsername());
	        String autority= null;
	        for (GrantedAuthority s : user.getAuthorities() ) {
	            
	            autority = s.getAuthority();
	            
	        }
	        
	        
	        // Vrati token kao odgovor na uspesno autentifikaciju
	        return ResponseEntity.ok(new UserTokenState(jws,user.getId(),autority));
	    }

	    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
	    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
	        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }

	    static class PasswordChanger {
	        public String oldPassword;
	        public String newPassword;
	    }
	    @RequestMapping(value="/register", method = RequestMethod.POST)
		public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDto)
													{
		 
		 	User username=userService.getByUsername(userDto.getUsername());
			if(username != null) {
				return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
			}
			User user = new User();
			user.setUsername(userDto.getUsername());
			user.setPassword(passwordencoder.encode(userDto.getPassword()));
			user.setFirstname(userDto.getFirstname());
			user.setLastname(userDto.getLastname());
			
			Authority a = asi.getByName(userDto.getAuthority());
			Set<Authority> authorities= new HashSet<>();
			authorities.add(a);
			user.setUser_authorities(authorities);
			userService.save(user);
			
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		}
}
