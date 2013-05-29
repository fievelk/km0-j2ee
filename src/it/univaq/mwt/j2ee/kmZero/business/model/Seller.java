package it.univaq.mwt.j2ee.kmZero.business.model;

import java.util.Date;

public class Seller extends User {

	private String p_iva;
	private String cod_fisc;
	private String company;
	
	// This constructor passes the oid to the User class, instantiating an User object with that oid
	public Seller(Long oid, String company) { 
		super(oid);
		this.company = company;
	}

	public Seller(long oid, String name, String surname, String email, String password, Date created, Date date_of_birth,
			Date last_access, String address, String p_iva, String cod_fisc, String company) {
		super(oid, name, surname, email, password, created, date_of_birth, last_access,
				address);
		this.p_iva = p_iva;
		this.cod_fisc = cod_fisc;
		this.company = company;
	}

	public Seller(String p_iva, String cod_fisc, String company) {
		this.p_iva = p_iva;
		this.cod_fisc = cod_fisc;
		this.company = company;
	}

	public String getP_iva() {
		return p_iva;
	}

	public void setP_iva(String p_iva) {
		this.p_iva = p_iva;
	}

	public String getCod_fisc() {
		return cod_fisc;
	}

	public void setCod_fisc(String cod_fisc) {
		this.cod_fisc = cod_fisc;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
}
