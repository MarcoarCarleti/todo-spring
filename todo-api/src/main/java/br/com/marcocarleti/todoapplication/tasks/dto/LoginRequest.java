package br.com.marcocarleti.todoapplication.tasks.dto;

public class LoginRequest {
	
	private String email;
	
	private String password;
	
	private Boolean admin;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	
	
}
