package it.univaq.mwt.j2ee.kmZero.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.model.Category;
import it.univaq.mwt.j2ee.kmZero.business.model.Image;
import it.univaq.mwt.j2ee.kmZero.business.model.Product;
import it.univaq.mwt.j2ee.kmZero.business.service.ProductService;

public class JDBCProductService implements ProductService{

	private DataSource dataSource;
	
	public JDBCProductService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	
	@Override
	public void createProduct(Product product) throws BusinessException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String sql = "INSERT INTO products (id_product, name, description, price, date_in, date_out, category_id, image_id)"
					
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateProduct(Product product) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(long oid_product) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> viewProducts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createCategory(Category category) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCategory(Category category) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCategory(long oid_category) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Category> findAllCategories() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
