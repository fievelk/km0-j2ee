package it.univaq.mwt.j2ee.kmZero.presentation.users;

import org.apache.struts.validator.ValidatorForm;

public class SellerCreateForm extends ValidatorForm{
	
	private long oid;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String confirm_password;
	private String created_data;
	private String date_of_birth_data;
	private String address;
	private String p_iva;
	private String cod_fisc;
	private String company;
	private String url;
	private String phone;
	
	
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
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public String getCreated_data() {
		return created_data;
	}
	public void setCreated_data(String created_data) {
		this.created_data = created_data;
	}
	public String getDate_of_birth_data() {
		return date_of_birth_data;
	}
	public void setDate_of_birth_data(String date_of_birth_data) {
		this.date_of_birth_data = date_of_birth_data;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
