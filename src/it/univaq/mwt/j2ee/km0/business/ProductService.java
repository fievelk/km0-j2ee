package it.univaq.mwt.j2ee.km0.business;

import java.util.List;

import it.univaq.mwt.j2ee.km0.business.model.Category;
import it.univaq.mwt.j2ee.km0.business.model.Product;

public interface ProductService {
		
	void createProduct(Product product);
		
	void updateProduct(Product product);
		
	void deleteProduct(long  oid_product);

	List<Product> viewProducts();
	
	void createCategory(Category category);
	
	void updateCategory(Category category);
	
	void deleteCategory(long oid_category);
	
	List<Category> findAllCategories();
	
	
	// inserire ResponseGrid
	
	
}