package br.com.marcocarleti.todoapplication.tasks.services.jwt;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.marcocarleti.todoapplication.tasks.entities.Customer;
import br.com.marcocarleti.todoapplication.tasks.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements UserDetailsService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Customer customer = customerRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("Email incorreto. Tem certeza que o email é: " + email + "?"));
		
		return  new User(customer.getEmail(), customer.getPassword(), Collections.emptyList());

	}

}
