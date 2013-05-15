package it.univaq.mwt.j2ee.km0.business.model;

import java.util.Date;

public class User {

	private long oid;
	private String name;
	private String surname;
	private String email;
	private String password;
	private Date created;
	private Date date_of_birth;
	private Date last_access;
	

	public User() {
		
	}
	
	public User(long oid, String name, String surname, String email,
			String password, Date created, Date date_of_birth,
			Date last_access) {
		super();
		this.oid = oid;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.created = created;
		this.date_of_birth = date_of_birth;
		this.last_access = last_access;
	}

	public long getOid() {
		return oid;
	}
	public void setOid(long oid) {
		this.oid = oid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Date getLast_access() {
		return last_access;
	}
	public void setLast_access(Date last_access) {
		this.last_access = last_access;
	}
	
	
	
}
