package br.com.marcocarleti.todoapplication.tasks.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.marcocarleti.todoapplication.tasks.dto.SignupRequest;
import br.com.marcocarleti.todoapplication.tasks.entities.Customer;
import br.com.marcocarleti.todoapplication.tasks.repositories.CustomerRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Customer createCustomer(SignupRequest signupRequest) {
		if (customerRepository.existsByEmail(signupRequest.getEmail())) {
			return null;
		}
		
	
		
		Customer customer = new Customer();
		BeanUtils.copyProperties(signupRequest, customer);
		
		String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
		customer.setPassword(hashPassword);
		customerRepository.save(customer);
		return customer;
	}

}
