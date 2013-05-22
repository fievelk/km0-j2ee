package it.univaq.mwt.j2ee.km0.presentation.products;

import it.univaq.mwt.j2ee.km0.business.model.Category;
import it.univaq.mwt.j2ee.km0.business.model.Image;

import java.util.Collection;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class ProductForm extends ValidatorForm {

	private long oid;
	private String name;
	private String description;
	private float price;
	private Date data_in;
	private Date data_out;
	private Category category; // Va inserito un long al posto del tipo Category?
	private Collection<Image> images;

	
}