package it.univaq.mwt.j2ee.kmZero.presentation.products;

import it.univaq.mwt.j2ee.kmZero.business.model.Category;
import it.univaq.mwt.j2ee.kmZero.business.model.Image;

import java.util.Collection;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class ProductForm extends ValidatorForm {

	private long oid;
	private String name;
	private String description;
	private float price;
	//private Date date_in;
	//private Date date_out;
	//private Category category; // Va inserito un long al posto del tipo Category?
	//private Collection<Image> images;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

/*	public Date getDate_in() {
		return date_in;
	}
	public void setDate_in(Date date_in) {
		this.date_in = date_in;
	}
	public Date getDate_out() {
		return date_out;
	}
	public void setDate_out(Date date_out) {
		this.date_out = date_out;
	}*/

	
}