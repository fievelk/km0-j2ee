package it.univaq.mwt.j2ee.kmZero.business.model;

import java.util.Calendar;

public class Seller extends User {

	private String p_iva;
	private String cod_fisc;
	private String company;
	private String url;
	private String phone;
	private int enable;
	
	public Seller() {
		super();
	}
	
	// Construttore con solo l'id
	public Seller(long user_oid){
		super (user_oid);
	}

	// Costruttore da utilizzare quando il venditore si registra da zero.
	public Seller(long oid, String name, String surname, String email, String password, Calendar created, Calendar date_of_birth,
			Calendar last_access, String address, String p_iva, String cod_fisc, String company, String url, String phone, int enable) {
		super(oid, name, surname, email, password, created, date_of_birth, last_access,
				address);
		this.p_iva = p_iva;
		this.cod_fisc = cod_fisc;
		this.company = company;
		this.url = url;
		this.phone = phone;
		this.enable = 0;
	}

	// Costruttore da utilizzare quando un utente fa l'upgrade a venditore
	public Seller(String p_iva, String cod_fisc, String company, String url, String phone) {
		this.p_iva = p_iva;
		this.cod_fisc = cod_fisc;
		this.company = company;
		this.url = url;
		this.phone = phone;
		this.enable = 0;
	}
	
	// Costruttore con Id User e nome della Company utilizzato per la visualizzazione dei prodotti di un venditore
	 public Seller(long oid, String company) {
		super(oid);
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

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}
	
}
