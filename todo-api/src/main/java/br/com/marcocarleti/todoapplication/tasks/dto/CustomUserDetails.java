package br.com.marcocarleti.todoapplication.tasks.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Boolean admin;
    private final List<String> roles;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Boolean admin, List<String> roles) {
        super(username, password, authorities);
        this.admin = admin;
        this.roles = roles;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public List<String> getRoles() {
        return roles;
    }
}
