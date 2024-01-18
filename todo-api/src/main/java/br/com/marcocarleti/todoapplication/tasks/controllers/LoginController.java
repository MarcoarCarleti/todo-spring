package br.com.marcocarleti.todoapplication.tasks.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcocarleti.todoapplication.tasks.dto.LoginRequest;
import br.com.marcocarleti.todoapplication.tasks.dto.LoginResponse;
import br.com.marcocarleti.todoapplication.tasks.services.jwt.CustomerServiceImpl;
import br.com.marcocarleti.todoapplication.tasks.utils.JwtUtil;

@RestController
@RequestMapping(value ="/login", 
		  consumes = MediaType.APPLICATION_JSON_VALUE, 
		  produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	 @PostMapping
	    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws IOException {
	        try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException("Incorrect email or password.");
	        } catch (DisabledException disabledException) {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer is not activated");
	            return null;
	        }
	        final UserDetails userDetails = customerServiceImpl.loadUserByUsername(loginRequest.getEmail());
	        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

	        return new LoginResponse(jwt);
	    }
	
}
