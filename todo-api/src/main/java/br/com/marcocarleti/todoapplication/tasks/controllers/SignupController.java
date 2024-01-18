package br.com.marcocarleti.todoapplication.tasks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcocarleti.todoapplication.tasks.dto.SignupRequest;
import br.com.marcocarleti.todoapplication.tasks.entities.Customer;
import br.com.marcocarleti.todoapplication.tasks.services.AuthService;

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "http://localhost:4200/*")
public class SignupController {

	@Autowired
	private AuthService authService;

	@PostMapping
	public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
		Customer createdCustomer = authService.createCustomer(signupRequest);
		if (createdCustomer != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer");
		}
	}
}
