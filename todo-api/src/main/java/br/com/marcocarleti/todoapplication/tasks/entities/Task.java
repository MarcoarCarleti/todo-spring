package br.com.marcocarleti.todoapplication.tasks.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	private boolean done;

	private String customerEmail;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Task() {

	}

	public Task(Long id, String title, String description, boolean done, String customerEmail) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.done = done;
		this.customerEmail = customerEmail;
	}

	public Long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public Boolean getDone() {
		return this.done;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String CustomerEmail() {
		return customerEmail;
	}

	public void setCustomerId(String customerEmail) {
		this.customerEmail = customerEmail;
	}

}