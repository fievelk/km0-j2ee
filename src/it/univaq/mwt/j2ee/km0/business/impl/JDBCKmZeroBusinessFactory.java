package it.univaq.mwt.j2ee.km0.business.impl;

import javax.sql.DataSource;

import it.univaq.mwt.j2ee.km0.business.KmZeroBusinessFactory;
import it.univaq.mwt.j2ee.km0.business.service.CartService;
import it.univaq.mwt.j2ee.km0.business.service.ImageService;
import it.univaq.mwt.j2ee.km0.business.service.ProductService;
import it.univaq.mwt.j2ee.km0.business.service.UserService;

public class JDBCKmZeroBusinessFactory extends KmZeroBusinessFactory{
	
	private DataSource dataSource;

	public JDBCKmZeroBusinessFactory(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public UserService getUserService() {
		return new JDBCUserService(dataSource);
	}

	@Override
	public CartService getCartService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductService getProductService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageService getImageService() {
		// TODO Auto-generated method stub
		return null;
	}

}
