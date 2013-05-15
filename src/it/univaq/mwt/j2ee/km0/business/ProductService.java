package it.univaq.mwt.j2ee.km0.business;

import java.util.List;

import it.univaq.mwt.j2ee.km0.business.model.Product;

public interface ProductService {
		
	void createProduct(Product product);
		
	void updateProduct(Product product);
		
	void deleteProduct(Product product);

	List<Product> viewProducts();
	
	// inserire ResponseGrid
	// il findAllCategories va messo in CategoryService;
}