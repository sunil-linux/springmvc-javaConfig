package com.agilent.earray8.springconfig.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty
	@Email
	@Size(min = 3, max = 80)
	private String email;

	@NotNull
	@Size(min = 2, max = 80)
	private String name;

	public User() {
	}

	public User(long id) {
		this.id = id;
	}

	public User(String name, String email) {
		this.email = email;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long value) {
		this.id = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + "]";
	}

}
