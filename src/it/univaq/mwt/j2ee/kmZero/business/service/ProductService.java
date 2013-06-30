package it.univaq.mwt.j2ee.kmZero.business.service;

import java.util.List;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.RequestGrid;
import it.univaq.mwt.j2ee.kmZero.business.ResponseGrid;
import it.univaq.mwt.j2ee.kmZero.business.model.Category;
import it.univaq.mwt.j2ee.kmZero.business.model.Product;

public interface ProductService {
		
	void createProduct(Product product) throws BusinessException;
		
	void updateProduct(Product product) throws BusinessException;
		
	void deleteProduct(Product product) throws BusinessException;

	List<Product> viewProducts() throws BusinessException;
	
	Product findProductById(long id) throws BusinessException;
	
	void createCategory(Category category) throws BusinessException;
	
	void updateCategory(Category category) throws BusinessException;
	
	void deleteCategory(long oid_category) throws BusinessException;
	
	List<Category> findAllCategories() throws BusinessException;

	ResponseGrid viewProductsBySellerIdPaginated(RequestGrid requestGrid) throws BusinessException;
	
	// inserire ResponseGrid
	
	
}
