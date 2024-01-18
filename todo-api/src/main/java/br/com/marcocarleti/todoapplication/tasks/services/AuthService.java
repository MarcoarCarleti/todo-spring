package br.com.marcocarleti.todoapplication.tasks.services;

import org.springframework.stereotype.Service;

import br.com.marcocarleti.todoapplication.tasks.dto.SignupRequest;
import br.com.marcocarleti.todoapplication.tasks.entities.Customer;

@Service
public interface AuthService {

	Customer createCustomer(SignupRequest signupRequest);

}
