package it.univaq.mwt.j2ee.km0.business;

import it.univaq.mwt.j2ee.km0.business.service.CartService;
import it.univaq.mwt.j2ee.km0.business.service.ImageService;
import it.univaq.mwt.j2ee.km0.business.service.ProductService;
import it.univaq.mwt.j2ee.km0.business.service.UserService;

public abstract class KmZeroBusinessFactory {
	
	private static KmZeroBusinessFactory instance;
	
	public static KmZeroBusinessFactory getInstance(){
		return instance;
	}
	
	public static void setInstance(KmZeroBusinessFactory factory){
		instance = factory;
	}
	
	public abstract UserService getUserService();
	public abstract CartService getCartService();
	public abstract ProductService getProductService();
	public abstract ImageService getImageService();

}
