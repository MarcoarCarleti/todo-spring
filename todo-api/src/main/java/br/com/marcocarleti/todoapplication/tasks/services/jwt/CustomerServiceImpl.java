package br.com.marcocarleti.todoapplication.tasks.services.jwt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.marcocarleti.todoapplication.tasks.dto.CustomUserDetails;
import br.com.marcocarleti.todoapplication.tasks.entities.Customer;
import br.com.marcocarleti.todoapplication.tasks.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements UserDetailsService {

    @Autowired
	private CustomerRepository customerRepository;
   
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email incorreto. Tem certeza que o email é: " + email + "?"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customer.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER"));

        List<String> roles = Arrays.asList("ROLE_USER"); // Adicione as roles do seu sistema aqui

        return new CustomUserDetails(customer.getEmail(), customer.getPassword(), authorities, customer.isAdmin(), roles);
    }

   
}
