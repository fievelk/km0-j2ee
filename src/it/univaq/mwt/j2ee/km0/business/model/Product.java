package it.univaq.mwt.j2ee.km0.business.model;

import java.util.Collection;
import java.util.Date;

public class Product {

	private long oid;
	private String name;
	private String description;
	private float price;
	private Date data_in;
	private Date data_out;
	private Category category;
	private Collection<Image> images;


	public Product() {
		super();
	}

	public Product(long oid, String name, String description, float price,
			Date data_in, Date data_out, Category category,
			Collection<Image> images) {
		super();
		this.oid = oid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.data_in = data_in;
		this.data_out = data_out;
		this.category = category;
		this.images = images;
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
	public Date getData_in() {
		return data_in;
	}
	public void setData_in(Date data_in) {
		this.data_in = data_in;
	}
	public Date getData_out() {
		return data_out;
	}
	public void setData_out(Date data_out) {
		this.data_out = data_out;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Collection<Image> getImages() {
		return images;
	}
	public void setImages(Collection<Image> images) {
		this.images = images;
	}

	
	
}
